package br.com.iddog.data

import br.com.iddog.data.network.breed.BreedCategoryResponse
import br.com.iddog.data.network.user.UserAccountPayload
import br.com.iddog.data.network.user.UserAccountResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.okhttp.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import kotlin.random.Random.Default.nextInt

object PayloadGenerator {
    const val BASE_URL: String = "/"

    fun generateUserAccount() = UserAccountPayload(
        userAccount = UserAccountResponse(
            userId = UUID.randomUUID().toString(),
            email = UUID.randomUUID().toString(),
            token = UUID.randomUUID().toString(),
            createdAt = UUID.randomUUID().toString(),
            updatedAt = UUID.randomUUID().toString(),
            v = nextInt()
        )
    )

    fun generateBreedCategory(breed: String) = BreedCategoryResponse(
        breed,
        listOf()
    )
}