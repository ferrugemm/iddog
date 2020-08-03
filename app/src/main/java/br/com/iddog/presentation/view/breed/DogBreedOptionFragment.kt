package br.com.iddog.presentation.view.breed

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.iddog.R
import br.com.iddog.crosscuting.Image
import br.com.iddog.domain.breed.DogBreed
import br.com.iddog.presentation.viewmodel.breed.DogBreedOptionViewModel
import kotlinx.android.synthetic.main.fragment_dog_breed_option.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class DogBreedOptionFragment : Fragment() {
    private val dogBreedOptionViewModel by viewModel<DogBreedOptionViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dog_breed_option, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivPug.setImageBitmap(applyScaleDownToImage(R.drawable.pug_category))
        ivLabrador.setImageBitmap(applyScaleDownToImage(R.drawable.labrador_category))
        ivHusky.setImageBitmap(applyScaleDownToImage(R.drawable.husky_category))
        ivHound.setImageBitmap(applyScaleDownToImage(R.drawable.hound_category))

        ivLabrador.setOnClickListener {
            applyScaleAnimation(it)
            defineCategoryButton(R.string.labrador_category)
            holdLastBreedOption(DogBreed.LABRADOR.toString().toLowerCase(Locale.ROOT))
        }

        ivHusky.setOnClickListener {
            applyScaleAnimation(it)
            defineCategoryButton(R.string.husky_category)
            holdLastBreedOption(DogBreed.HUSKY.toString().toLowerCase(Locale.ROOT))
        }

        ivPug.setOnClickListener {
            applyScaleAnimation(it)
            defineCategoryButton(R.string.pug_category)
            holdLastBreedOption(DogBreed.PUG.toString().toLowerCase(Locale.ROOT))
        }

        ivHound.setOnClickListener {
            applyScaleAnimation(it)
            defineCategoryButton(R.string.hound_category)
            holdLastBreedOption(DogBreed.HOUND.toString().toLowerCase(Locale.ROOT))
        }

        mbDogChoseed.setOnClickListener {
            findNavController().navigate(
                DogBreedOptionFragmentDirections.actionDogBreedOptionFragmentToDogBreedListFragment(
                    dogBreedOptionViewModel.retrieveLastBreedOption()
                )
            )
        }
    }

    private fun applyScaleDownToImage(drawableId: Int): Bitmap? {
        return ContextCompat.getDrawable(
            requireContext(),
            drawableId
        )?.let {
            Image.scaleDown(it, requireContext())
        }
    }

    private fun applyScaleAnimation(view: View) {
        view.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.scale_image))
    }

    private fun defineCategoryButton(categoryId: Int) {
        mbDogChoseed.visibility = View.VISIBLE
        mbDogChoseed.text = getString(R.string.next_with, getString(categoryId))
    }

    private fun holdLastBreedOption(breed: String) {
        dogBreedOptionViewModel.holdBreedOption(breed)
    }
}