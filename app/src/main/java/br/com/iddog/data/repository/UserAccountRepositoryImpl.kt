package br.com.iddog.data.repository

import br.com.iddog.data.adapter.UserAccountAdapter
import br.com.iddog.data.local.UserDAO
import br.com.iddog.data.network.IdDogService
import br.com.iddog.data.network.ktx.requestCatching
import br.com.iddog.data.network.user.UserAccountRequest
import br.com.iddog.domain.user.UserAccountData
import br.com.iddog.domain.user.UserAccountRepository

class UserAccountRepositoryImpl constructor(
    private val idDogService: IdDogService,
    private val userDAO: UserDAO
) : UserAccountRepository {

    override suspend fun getUserAccount(vararg credentials: String) = requestCatching {
        val (username) = credentials
        idDogService.loginUserAccount(UserAccountRequest(username))
    }

    override suspend fun saveUserData(userAccount: UserAccountData) {
        userDAO.saveUserAccount(UserAccountAdapter.toUserAccountEntitiy(userAccount))
    }
}