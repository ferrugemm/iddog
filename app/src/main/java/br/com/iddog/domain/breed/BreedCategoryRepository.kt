package br.com.iddog.domain.breed

import br.com.iddog.data.network.breed.BreedCategoryResponse

interface BreedCategoryRepository {
    suspend fun findBreedImagesByCategoryName(category: String): Result<BreedCategoryResponse>
}