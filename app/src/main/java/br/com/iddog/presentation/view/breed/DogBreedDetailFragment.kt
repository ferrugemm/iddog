package br.com.iddog.presentation.view.breed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import br.com.iddog.R
import coil.api.load
import kotlinx.android.synthetic.main.fragment_dog_breed_detail.*

class DogBreedDetailFragment : Fragment() {
    private val dogBreedDetailsArgs: DogBreedDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dog_breed_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ivDogBreedDetail.load(dogBreedDetailsArgs.breedOptionSelected)
    }
}