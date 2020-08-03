package br.com.iddog.domain.user

data class UserAccountData(
    val userId: String,
    val email: String,
    val token: String,
    val createdAt: String,
    val updatedAt: String,
    val v: Int //TODO: Descobrir o que significa a info
)