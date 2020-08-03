package br.com.iddog.presentation.viewmodel.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.iddog.data.network.NetworkError
import br.com.iddog.domain.user.UserInfo
import br.com.iddog.domain.user.UserLoginUseCase
import br.com.iddog.presentation.state.UserAccountStates
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserAccountViewModel constructor(private val loginUseCase: UserLoginUseCase) : ViewModel() {
    private val userAccountLiveData = MutableLiveData<UserAccountStates>()
    val userAccountObservable: LiveData<UserAccountStates> = userAccountLiveData

    fun signUpUser(email: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                loginUseCase(UserInfo(email))
            }
            result.onSuccess {
                userAccountLiveData.postValue(UserAccountStates.Success)
            }
            result.onFailure {
                when (it) {
                        is NetworkError.Client -> userAccountLiveData.postValue(UserAccountStates.NetworkError)
                        is NetworkError.Server -> userAccountLiveData.postValue(UserAccountStates.NetworkError)
                        is NetworkError.Unavailable -> userAccountLiveData.postValue(UserAccountStates.NetworkError)
                        is NetworkError.Unknown -> userAccountLiveData.postValue(UserAccountStates.Unknown)
                        is IllegalArgumentException -> userAccountLiveData.postValue(
                            UserAccountStates.InvalidEmail)
                        else -> userAccountLiveData.postValue(UserAccountStates.Unknown)
                }
            }
        }
    }
}