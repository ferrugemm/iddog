package br.com.iddog.data.user

import br.com.iddog.data.PayloadGenerator
import br.com.iddog.data.local.UserDAO
import br.com.iddog.data.network.user.UserAccountPayload
import br.com.iddog.data.network.user.UserAccountRequest
import br.com.iddog.data.network.IdDogService
import br.com.iddog.data.repository.UserAccountRepositoryImpl
import br.com.iddog.domain.user.UserAccountRepository
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.util.*

class UserAccountRepositoryTest {
    private val service: IdDogService = mockk()
    private val userData: UserDAO = spyk()
    private lateinit var userAccountRepository: UserAccountRepository
    private val response: UserAccountPayload get() = PayloadGenerator.generateUserAccount()

    @Before
    fun setup() {
        userAccountRepository = UserAccountRepositoryImpl(service, userData)
    }

    @Test
    fun `login success`() = runBlocking {
        coEvery { service.loginUserAccount(request) } returns response

        assertTrue(userAccountRepository.getUserAccount(request.email).isSuccess)
    }

    companion object {
        val request: UserAccountRequest =
            UserAccountRequest(UUID.randomUUID().toString())
    }
}