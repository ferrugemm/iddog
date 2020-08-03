package br.com.iddog.presentation.view.breed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.iddog.R
import br.com.iddog.presentation.viewmodel.breed.DogBreedListViewModel
import coil.ImageLoader
import kotlinx.android.synthetic.main.fragment_dog_breed_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DogBreedListFragment : Fragment() {
    private val dogBreedListViewModel by viewModel<DogBreedListViewModel>()
    private val dogBreedCategory: DogBreedListFragmentArgs by navArgs()
    private val dogBreedOptionAdapter by lazy { DogBreedOptionAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dogBreedListViewModel.loadDogBreed(dogBreedCategory.breedOption)
        return inflater.inflate(R.layout.fragment_dog_breed_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        setupObservables()

        dogBreedOptionAdapter.onDogBreedSelected {
            findNavController().navigate(
                DogBreedListFragmentDirections.actionDogBreedListFragmentToDogBreedDetailFragment(
                    it
                )
            )
        }
    }

    private fun setupObservables() {
        dogBreedListViewModel.breedsObservable.observe(viewLifecycleOwner, Observer {
            pbCategory.visibility = View.INVISIBLE
            rvDogBreed.visibility = View.VISIBLE

            dogBreedOptionAdapter.addDogBreedPhotos(it)
        })
    }

    private fun setupRecycler() {
        rvDogBreed.setHasFixedSize(true)
        rvDogBreed.adapter = dogBreedOptionAdapter
    }
}