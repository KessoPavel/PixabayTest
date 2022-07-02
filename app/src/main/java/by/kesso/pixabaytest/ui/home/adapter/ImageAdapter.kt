package by.kesso.pixabaytest.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.kesso.pixabaytest.R
import by.kesso.pixabaytest.databinding.ItemImageBinding
import by.kesso.pixabaytest.domain.entity.PixaImage
import coil.load
import coil.transform.RoundedCornersTransformation

class ImageAdapter(
    private val onClick: (PixaImage) -> Unit
): PagingDataAdapter<PixaImage, ImageAdapter.ImageViewHolder>(REPO_COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImageBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        with(holder) {
            getItem(position)?.apply {
                binding.tvUserName.text = user
                binding.ivPreview.load(previewURL) {
                    crossfade(true)
                    placeholder(R.drawable.image_placeholder)
                    transformations(RoundedCornersTransformation())
                }
                holder.itemView.setOnClickListener {
                    onClick(this)
                }
            }
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

}