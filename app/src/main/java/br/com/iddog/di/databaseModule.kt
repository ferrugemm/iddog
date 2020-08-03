package br.com.iddog.di

import androidx.room.Room
import br.com.iddog.data.local.IddogDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val roomDatabaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            IddogDatabase::class.java,
            "IddogDatabase.db"
        ).build()
    }

    single {
        get<IddogDatabase>().userDAO()
    }
}