package br.com.iddog

import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.iddog.data.local.IddogDatabase
import br.com.iddog.data.local.UserAccountEntity
import br.com.iddog.data.local.UserDAO
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.koin.test.KoinTest
import org.koin.test.inject

@RunWith(AndroidJUnit4::class)
class UserDAOTest : KoinTest {
    private val idDogDatabase: IddogDatabase by inject()
    private val userDAO: UserDAO by inject()

    @Test
    fun testUserInsert() = runBlocking {
        val entity = UserAccountEntity(
            1,
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJpZGRvZy1zZXJ2aWNlIiw"
        )

        userDAO.saveUserAccount(entity)
        val entityInfo = userDAO.getUserAccount()

        assertEquals(entity.token, entityInfo.token)
    }

    @After
    fun disarrange() {
        idDogDatabase.close()
    }
}