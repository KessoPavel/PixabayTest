package by.kesso.pixabaytest.ui.imageDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kesso.pixabaytest.R
import by.kesso.pixabaytest.databinding.FragmentImageDetailBinding
import by.kesso.pixabaytest.ui.utils.next
import org.koin.androidx.viewmodel.ext.android.viewModel


class ImageDetailFragment: Fragment(), CropListener {
    private val viewModel: ImageDetailViewModel by viewModel()
    private val args: ImageDetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentImageDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_image_detail, container, false
        )
        binding.viewmodel = viewModel
        binding.listener = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.init(args.image)
    }

    override fun scale() {
        binding.ivImage.scaleType = binding.ivImage.scaleType.next()
    }
}

interface CropListener {
    fun scale()
}
