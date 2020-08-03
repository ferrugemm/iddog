package br.com.iddog.presentation.viewmodel.breed

import androidx.lifecycle.ViewModel

class DogBreedOptionViewModel : ViewModel() {
    private var lastBreedOption = ""

    fun holdBreedOption(breed: String) {
        lastBreedOption = breed
    }

    fun retrieveLastBreedOption(): String = lastBreedOption
}