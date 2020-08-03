package br.com.iddog.presentation.state

sealed class UserAccountStates {
    object Success : UserAccountStates()
    object NetworkError : UserAccountStates()
    object InvalidEmail : UserAccountStates()
    object Unknown : UserAccountStates()
}