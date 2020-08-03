package br.com.iddog

import androidx.multidex.MultiDexApplication
import br.com.iddog.data.network.IdDogService
import br.com.iddog.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class IddogApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@IddogApplication)
            modules(
                listOf(
                    viewModelModule,
                    useCaseModule,
                    repositoryModule,
                    roomDatabaseModule,
                    networkServiceModule<IdDogService>(ID_DOG_BASE_URL)
                )
            )
        }
    }

    companion object {
        const val ID_DOG_BASE_URL = "https://iddog-nrizncxqba-uc.a.run.app"
    }
}