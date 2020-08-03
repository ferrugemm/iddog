package br.com.iddog.di

import br.com.iddog.data.repository.BreedCategoryRepositoryImpl
import br.com.iddog.data.repository.UserAccountRepositoryImpl
import br.com.iddog.domain.breed.BreedCategoryRepository
import br.com.iddog.domain.user.UserAccountRepository
import br.com.iddog.domain.user.UserLoginUseCase
import br.com.iddog.presentation.viewmodel.breed.DogBreedListViewModel
import br.com.iddog.presentation.viewmodel.breed.DogBreedOptionViewModel
import br.com.iddog.presentation.viewmodel.breed.DogBreedViewModel
import br.com.iddog.presentation.viewmodel.user.UserAccountViewModel
import coil.ImageLoader
import coil.util.CoilUtils
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    factory<UserAccountRepository> { UserAccountRepositoryImpl(get(), get()) }
    factory<BreedCategoryRepository> { BreedCategoryRepositoryImpl(get(), get()) }
}

val useCaseModule = module {
    factory { UserLoginUseCase(userAccountRepository = get()) }
}

val viewModelModule = module {
    viewModel { UserAccountViewModel(loginUseCase = get()) }
    viewModel { DogBreedViewModel() }
    viewModel { DogBreedListViewModel(get()) }
    viewModel { DogBreedOptionViewModel() }
}