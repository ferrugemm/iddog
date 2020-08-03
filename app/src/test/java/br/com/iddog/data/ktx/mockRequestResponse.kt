package br.com.iddog.data.ktx

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.okhttp.mockwebserver.MockResponse

inline fun<reified P> P.mockResponse(responseCode: Int): MockResponse {
    val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    val json = moshi.adapter<P>(P::class.java).toJson(this)

    return MockResponse()
        .setBody(json)
        .setResponseCode(responseCode)
}