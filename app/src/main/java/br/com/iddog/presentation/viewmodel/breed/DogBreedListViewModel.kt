package br.com.iddog.presentation.viewmodel.breed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.iddog.domain.breed.BreedCategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DogBreedListViewModel(private val breedCategoryRepository: BreedCategoryRepository) : ViewModel() {
    private val breedsLiveData = MutableLiveData<List<String>>()
    val breedsObservable: LiveData<List<String>> = breedsLiveData

    fun loadDogBreed(categoryName: String) {
        viewModelScope.launch {
            val resultDogBreed = withContext(Dispatchers.IO) {
                breedCategoryRepository.findBreedImagesByCategoryName(categoryName)
            }
            resultDogBreed.onSuccess {
                breedsLiveData.postValue(it.categoryImageList)
            }.onFailure {
                breedsLiveData.postValue(listOf())
            }
        }
    }
}