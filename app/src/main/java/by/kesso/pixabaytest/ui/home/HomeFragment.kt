package by.kesso.pixabaytest.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kesso.pixabaytest.databinding.FragmentHomeFrahmentBinding
import by.kesso.pixabaytest.domain.entity.PixaImage
import by.kesso.pixabaytest.ui.home.adapter.ImageAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()
    private val adapter by lazy { ImageAdapter(onClick = ::onClick) }


    private var _binding: FragmentHomeFrahmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeFrahmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        observeViewModel()
    }

    private fun initView() {
        binding.rvList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvList.adapter = adapter
    }

    private fun observeViewModel() = with(viewModel) {
        list.observe(viewLifecycleOwner) {
            adapter.submitData(lifecycle, it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onClick(image: PixaImage) {
        val direction = HomeFragmentDirections.actionHomeFragmentToImageDetailFragment(image)
        findNavController().navigate(direction)
    }
}