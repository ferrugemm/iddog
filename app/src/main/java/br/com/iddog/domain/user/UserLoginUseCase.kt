package br.com.iddog.domain.user

import br.com.iddog.data.adapter.UserAccountAdapter

class UserLoginUseCase constructor(
    private val userAccountRepository: UserAccountRepository
) {
    suspend operator fun invoke(credentials: UserInfo): Result<UserAccountData> {
        return with(credentials.email.trim()) {
            if (validateEmailFormat(this)) {
                userAccountRepository.getUserAccount(this)
                    .mapCatching {
                        val account = UserAccountAdapter.fromAccountResponse(it)
                        userAccountRepository.saveUserData(account)
                        account
                    }
            } else {
                Result.failure(IllegalArgumentException("E-mail inválido"))
            }
        }
    }

    fun validateEmailFormat(username: String): Boolean {
        return username.find { it == '@' }?.isDefined() ?: false && username.first() != '@'
    }
}