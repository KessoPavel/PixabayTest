package by.kesso.pixabaytest.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kesso.pixabaytest.R
import by.kesso.pixabaytest.databinding.ItemLoadBinding

class ImageLoadStateAdapter(
    private val retryCallback: () -> Unit
) : LoadStateAdapter<ImageLoadStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        with(holder.binding) {
            retry.isVisible = loadState !is LoadState.Loading
            errorMessage.isVisible = loadState !is LoadState.Loading
            progress.isVisible = loadState is LoadState.Loading

            if (loadState is LoadState.Error){
                errorMessage.text = loadState.error.localizedMessage
            }

            retry.setOnClickListener {
                retryCallback.invoke()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding: ItemLoadBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_load, parent, false)
        return LoadStateViewHolder(binding)
    }

    class LoadStateViewHolder(val binding: ItemLoadBinding) : RecyclerView.ViewHolder(binding.root)
}