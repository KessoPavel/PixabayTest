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
import org.koin.androidx.viewmodel.ext.android.viewModel


class ImageDetailFragment: Fragment() {
    private val viewModel: ImageDetailViewModel by viewModel()
    private val args: ImageDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentImageDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_image_detail, container, false
        )
        val view: View = binding.root
        binding.viewmodel = viewModel
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.init(args.image)
    }
}