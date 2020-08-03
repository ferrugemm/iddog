package br.com.iddog.data.network.ktx

import br.com.iddog.data.network.NetworkError
import kotlinx.coroutines.CancellationException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.NoRouteToHostException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend fun <T, R> T.requestCatching(
    onResponse: suspend T.() -> R
): Result<R> {
    return try {
        val response: R = onResponse()
        Result.success(response)
    } catch (incoming: Throwable) {
        val error = incoming.obtainRequestError()
        Result.failure(error)
    }
}

internal fun Throwable.obtainRequestError(): NetworkError {
    return if (this is HttpException) {
        treatmentHttpError()
    } else {
        treatmentNetworkingError()
    }
}

internal fun HttpException.treatmentHttpError(): NetworkError {
    return when (code()) {
        401 -> NetworkError.Unauthorized
        in 300..499 -> NetworkError.Client
        else -> NetworkError.Server
    }
}

internal fun Throwable.treatmentNetworkingError(): NetworkError {
    return when {
        cannotReachHost(this) -> {
            NetworkError.Unavailable
        }

        isRequestCanceled(this) -> {
            NetworkError.RequestCancelled
        }

        else -> NetworkError.Unknown
    }
}


private fun cannotReachHost(error: Throwable): Boolean {
    return error is UnknownHostException || error is ConnectException || error is NoRouteToHostException || error is SocketTimeoutException
}

private fun isRequestCanceled(error: Throwable): Boolean {
    return error is CancellationException
}