package br.com.iddog.data.repository

import br.com.iddog.data.local.UserDAO
import br.com.iddog.data.network.IdDogService
import br.com.iddog.data.network.breed.BreedCategoryResponse
import br.com.iddog.data.network.ktx.requestCatching
import br.com.iddog.domain.breed.BreedCategoryRepository

class BreedCategoryRepositoryImpl(private val service: IdDogService, private val dao: UserDAO) :
    BreedCategoryRepository {

    override suspend fun findBreedImagesByCategoryName(category: String) = requestCatching {
        service.getBreedsCategory(category, dao.getUserAccount().token)
    }
}
