package br.com.iddog.data.breed

import br.com.iddog.data.PayloadGenerator
import br.com.iddog.data.ktx.mockResponse
import br.com.iddog.data.ktx.mockRetrofitService
import br.com.iddog.data.network.IdDogService
import br.com.iddog.data.network.breed.BreedCategoryResponse
import com.squareup.okhttp.mockwebserver.MockWebServer
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import java.util.*

class BreedCategoryRemoteTest {
    @get:Rule
    val mockWebServer = MockWebServer()

    @Test
    fun `get list of breed pug category remotely`() = runBlocking {
        val pugResponse = PayloadGenerator.generateBreedCategory("pug")
        mockWebServer.enqueue(pugResponse.mockResponse(200))
        val idDogMockService = mockRetrofitService<IdDogService>(mockWebServer)

        Assert.assertEquals(
            BreedCategoryResponse("pug", listOf()),
            idDogMockService.getBreedsCategory("pug", UUID.randomUUID().toString())
        )
    }

    @Test
    fun `get list of breed hound category remotely`() = runBlocking {
        val houndResponse = PayloadGenerator.generateBreedCategory("hound")
        mockWebServer.enqueue(houndResponse.mockResponse(200))
        val idDogMockService = mockRetrofitService<IdDogService>(mockWebServer)

        Assert.assertEquals(
            BreedCategoryResponse("hound", listOf()),
            idDogMockService.getBreedsCategory("hound", UUID.randomUUID().toString())
        )
    }

    @Test
    fun `get list of breed labrador category remotely`() = runBlocking {
        val labradorResponse = PayloadGenerator.generateBreedCategory("labrador")
        mockWebServer.enqueue(labradorResponse.mockResponse(200))
        val idDogMockService = mockRetrofitService<IdDogService>(mockWebServer)

        Assert.assertEquals(
            BreedCategoryResponse("labrador", listOf()),
            idDogMockService.getBreedsCategory("labrador", UUID.randomUUID().toString())
        )
    }

    @Test
    fun `get list of breed husky category remotely`() = runBlocking {
        val huskyResponse = PayloadGenerator.generateBreedCategory("husky")
        mockWebServer.enqueue(huskyResponse.mockResponse(200))
        val idDogMockService = mockRetrofitService<IdDogService>(mockWebServer)

        Assert.assertEquals(
            BreedCategoryResponse("husky", listOf()),
            idDogMockService.getBreedsCategory("husky", UUID.randomUUID().toString())
        )
    }
}