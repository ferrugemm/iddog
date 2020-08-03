package br.com.iddog.data.network

sealed class NetworkError : Throwable() {
    object Unauthorized : NetworkError()
    object Client : NetworkError()
    object Server : NetworkError()
    object Unavailable : NetworkError()
    object RequestCancelled : NetworkError()
    object Unknown : NetworkError()
}
