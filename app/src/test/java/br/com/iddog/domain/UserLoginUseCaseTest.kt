package br.com.iddog.domain

import br.com.iddog.data.network.*
import br.com.iddog.data.network.user.UserAccountPayload
import br.com.iddog.data.network.user.UserAccountResponse
import br.com.iddog.domain.user.UserAccountRepository
import br.com.iddog.domain.user.UserInfo
import br.com.iddog.domain.user.UserLoginUseCase
import io.mockk.coEvery
import io.mockk.spyk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class UserLoginUseCaseTest {
    private val userAccountRepository: UserAccountRepository = spyk()
    private lateinit var userLoginUseCase: UserLoginUseCase

    @Before
    fun setup() {
        userLoginUseCase =
            UserLoginUseCase(userAccountRepository)
    }

    @Test
    fun `login with successfully`() = runBlocking {
        val credentials =
            UserInfo("test_user@gmail.com")

        coEvery {
            userAccountRepository.getUserAccount(
                credentials.email
            )
        } returns Result.success(
            UserAccountPayload(
                UserAccountResponse(
                    userId = "5f2354499c04b5392255a794",
                    email = "Jose da Silva Teste",
                    token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJpZGRvZy1zZXJ2aWNlIiw",
                    createdAt = "2020-07-30T23:14:17.649Z",
                    updatedAt = "2020-07-30T23:14:17.649Z",
                    v = 0
                )
            )
        )
        val result = userLoginUseCase.invoke(credentials)
        assertEquals(true, result.isSuccess)
    }

    @Test
    fun `login with incorrect password`() = runBlocking {
        val credentials =
            UserInfo("test_user@gmail.com")

        coEvery {
            userAccountRepository.getUserAccount(
                credentials.email
            )
        } returns Result.failure(NetworkError.Unauthorized)

        val result = userLoginUseCase.invoke(credentials)
        assertEquals(true, result.isFailure)
    }

    @Test
    fun `validate the correct email`() = runBlocking {
        val credentials =
            UserInfo("test_user@gmail.com")

        val isCorrect = userLoginUseCase.validateEmailFormat(credentials.email)
        assertTrue(isCorrect)
    }

    @Test
    fun `validate the incorrect email`() = runBlocking {
        val credentials = UserInfo("@gmail.com")

        val isCorrect = userLoginUseCase.validateEmailFormat(credentials.email)
        assertFalse(isCorrect)
    }

    @Test
    fun `validate space for correct email`() = runBlocking {
        val credentials = UserInfo("lfernandodesenv@gmail.com     ")

        val isCorrect = userLoginUseCase.validateEmailFormat(credentials.email)
        assertTrue(isCorrect)
    }
}