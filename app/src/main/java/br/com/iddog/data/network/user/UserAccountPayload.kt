package br.com.iddog.data.network.user

import com.squareup.moshi.Json

data class UserAccountPayload(@Json(name = "user") val userAccount: UserAccountResponse)