package br.com.iddog.data.network.breed

import com.squareup.moshi.Json

data class BreedCategoryResponse(
    @Json(name = "category") val categoryName: String,
    @Json(name = "list") val categoryImageList: List<String>
)