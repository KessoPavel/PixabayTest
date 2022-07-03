package by.kesso.pixabaytest.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.kesso.pixabaytest.R
import by.kesso.pixabaytest.databinding.ItemImageBinding
import by.kesso.pixabaytest.domain.entity.PixaImage

class ImageAdapter(
    private val listener: ClickListener
): PagingDataAdapter<PixaImage, ImageAdapter.ImageViewHolder>(REPO_COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding: ItemImageBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_image, parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        with(holder) {
            binding.item = getItem(position)
            binding.listener = listener
        }
    }

    inner class ImageViewHolder(val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<PixaImage>() {
            override fun areItemsTheSame(oldItem: PixaImage, newItem: PixaImage): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: PixaImage, newItem: PixaImage): Boolean =
                oldItem == newItem
        }
    }

    fun interface ClickListener {
        fun onClick(image: PixaImage)
    }
}