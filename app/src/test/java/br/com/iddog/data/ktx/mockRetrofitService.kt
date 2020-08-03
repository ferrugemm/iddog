package br.com.iddog.data.ktx

import br.com.iddog.data.PayloadGenerator
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.okhttp.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

inline fun <reified S> mockRetrofitService(mockWebServer: MockWebServer): S =
    Retrofit.Builder()
        .baseUrl(mockWebServer.url(PayloadGenerator.BASE_URL).url())
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
            )
        )
        .build()
        .create(S::class.java)