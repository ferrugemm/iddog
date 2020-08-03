package br.com.iddog.data.network.user

import com.squareup.moshi.Json

data class UserAccountResponse(
    @Json(name = "_id") val userId: String,
    @Json(name = "email") val email: String,
    @Json(name = "token") val token: String,
    @Json(name = "createdAt") val createdAt: String,
    @Json(name = "updatedAt") val updatedAt: String,
    @Json(name = "__v") val v: Int
)