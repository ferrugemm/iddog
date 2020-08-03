package br.com.iddog.data.network.user

import com.squareup.moshi.Json

data class UserAccountRequest(
    @Json(name = "email") val email: String
)