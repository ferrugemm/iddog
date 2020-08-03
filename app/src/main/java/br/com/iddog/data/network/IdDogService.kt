package br.com.iddog.data.network

import br.com.iddog.data.network.breed.BreedCategoryResponse
import br.com.iddog.data.network.user.UserAccountPayload
import br.com.iddog.data.network.user.UserAccountRequest
import retrofit2.http.*

interface IdDogService {
    @POST("/signup")
    suspend fun loginUserAccount(@Body credentials: UserAccountRequest): UserAccountPayload

    @GET("/feed")
    suspend fun getBreedsCategory(
        @Query("category") category: String,
        @Header("Authorization") token: String
    ): BreedCategoryResponse
}
