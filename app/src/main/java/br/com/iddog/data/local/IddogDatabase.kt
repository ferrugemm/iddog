package br.com.iddog.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [UserAccountEntity::class],
    version = IddogDatabase.DB_VERSION,
    exportSchema = false
)
abstract class IddogDatabase : RoomDatabase() {

    abstract fun userDAO(): UserDAO

    companion object {
        const val DB_VERSION = 1
    }
}