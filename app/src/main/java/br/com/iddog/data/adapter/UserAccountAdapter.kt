package br.com.iddog.data.adapter

import br.com.iddog.data.local.UserAccountEntity
import br.com.iddog.data.network.user.UserAccountPayload
import br.com.iddog.data.network.user.UserAccountResponse
import br.com.iddog.domain.user.UserAccountData

object UserAccountAdapter {
    fun toUserAccountResponse(userAccountData: UserAccountData) =
        UserAccountPayload(
            UserAccountResponse(
                userId = userAccountData.userId,
                createdAt = userAccountData.createdAt,
                updatedAt = userAccountData.updatedAt,
                token = userAccountData.token,
                email = userAccountData.email,
                v = userAccountData.v
            )
        )

    fun fromAccountResponse(userAccountPayload: UserAccountPayload) =
        UserAccountData(
            userId = userAccountPayload.userAccount.userId,
            email = userAccountPayload.userAccount.email,
            token = userAccountPayload.userAccount.token,
            updatedAt = userAccountPayload.userAccount.updatedAt,
            createdAt = userAccountPayload.userAccount.createdAt,
            v = userAccountPayload.userAccount.v
        )

    fun toUserAccountEntitiy(userAccount: UserAccountData) =
        UserAccountEntity(
            token = userAccount.token
        )
}