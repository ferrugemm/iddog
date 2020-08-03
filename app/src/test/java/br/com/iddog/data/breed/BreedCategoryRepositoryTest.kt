package br.com.iddog.data.breed

import br.com.iddog.data.PayloadGenerator
import br.com.iddog.data.local.UserAccountEntity
import br.com.iddog.data.local.UserDAO
import br.com.iddog.data.network.IdDogService
import br.com.iddog.data.repository.BreedCategoryRepositoryImpl
import br.com.iddog.domain.breed.BreedCategoryRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.*

class BreedCategoryRepositoryTest {
    private lateinit var breedCategoryRepositoryTest: BreedCategoryRepository
    private val service = mockk<IdDogService>()
    private val dao = mockk<UserDAO>()
    private val randomToken = UUID.randomUUID().toString()

    @Before
    fun setup() {
        breedCategoryRepositoryTest = BreedCategoryRepositoryImpl(service, dao)
        coEvery { dao.getUserAccount() } returns UserAccountEntity(token = randomToken)
    }

    @Test
    fun `get list of breed pug category`() = runBlocking {
        val pugCategory = "pug"
        val randomToken = UUID.randomUUID().toString()

        coEvery {
            service.getBreedsCategory(
                pugCategory,
                randomToken
            )
        } returns PayloadGenerator.generateBreedCategory("pug")

        coEvery { dao.getUserAccount() } returns UserAccountEntity(token = randomToken)

        assertEquals("pug",
            breedCategoryRepositoryTest.findBreedImagesByCategoryName(pugCategory)
                .getOrThrow().categoryName
        )
    }

    @Test
    fun `get list of breed labrador category`() = runBlocking {
        val labradorCategory = "labrador"
        val randomToken = UUID.randomUUID().toString()

        coEvery {
            service.getBreedsCategory(
                labradorCategory,
                randomToken
            )
        } returns PayloadGenerator.generateBreedCategory("labrador")

        coEvery { dao.getUserAccount() } returns UserAccountEntity(token = randomToken)

        assertEquals("labrador",
            breedCategoryRepositoryTest.findBreedImagesByCategoryName(labradorCategory)
                .getOrThrow().categoryName
        )
    }

    @Test
    fun `get list of breed husky category`() = runBlocking {
        val huskyCategory = "husky"

        coEvery {
            service.getBreedsCategory(
                huskyCategory,
                randomToken
            )
        } returns PayloadGenerator.generateBreedCategory("husky")

        assertEquals("husky",
            breedCategoryRepositoryTest.findBreedImagesByCategoryName(huskyCategory)
                .getOrThrow().categoryName
        )
    }

    @Test
    fun `get list of breed hound category`() = runBlocking {
        val houndCategory = "hound"

        coEvery {
            service.getBreedsCategory(
                houndCategory,
                randomToken
            )
        } returns PayloadGenerator.generateBreedCategory("hound")

        assertEquals("hound",
            breedCategoryRepositoryTest.findBreedImagesByCategoryName(houndCategory)
                .getOrThrow().categoryName
        )
    }
}