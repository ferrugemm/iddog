package br.com.iddog.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUserAccount(userAccount: UserAccountEntity)

    @Query("SELECT * FROM user")
    suspend fun getUserAccount(): UserAccountEntity
}