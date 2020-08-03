package br.com.iddog.domain.user

import br.com.iddog.data.network.user.UserAccountPayload

interface UserAccountRepository {
    suspend fun getUserAccount(vararg credentials: String): Result<UserAccountPayload>
    suspend fun saveUserData(userAccount: UserAccountData)
}