package br.com.iddog.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserAccountEntity(
    @PrimaryKey val uid: Int = 1,
    @ColumnInfo(name = "token") val token: String
)