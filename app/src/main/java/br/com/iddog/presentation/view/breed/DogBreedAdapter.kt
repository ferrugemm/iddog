package br.com.iddog.presentation.view.breed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.iddog.R
import coil.api.load
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.row_dog_breed.view.*

class DogBreedAdapter :
    RecyclerView.Adapter<DogBreedAdapter.DogBreedOptionViewHolder>() {
    private val dogBreedsPhotos = mutableListOf<String>()
    private var dogBreedsPhoto: (String) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogBreedOptionViewHolder {
        return LayoutInflater.from(parent.context).inflate(R.layout.row_dog_breed, parent, false)
            .run {
                DogBreedOptionViewHolder(this)
            }
    }

    override fun getItemCount(): Int = dogBreedsPhotos.size

    override fun onBindViewHolder(holder: DogBreedOptionViewHolder, position: Int) {
        holder.bindViewHolderData(dogBreedsPhotos[position])
    }

    fun addDogBreedPhotos(dogBreeds: List<String>) {
        dogBreedsPhotos.addAll(dogBreeds)
        notifyDataSetChanged()
    }

    fun onDogBreedSelected(listener: (String) -> Unit) {
        dogBreedsPhoto = listener
    }

    inner class DogBreedOptionViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindViewHolderData(item: String) {
            itemView.apply {
                ivDogBreed.load(item)
                ivDogBreed.setOnClickListener { dogBreedsPhoto(item) }
            }
        }
    }
}