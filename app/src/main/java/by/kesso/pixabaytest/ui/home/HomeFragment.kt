package by.kesso.pixabaytest.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kesso.pixabaytest.R
import by.kesso.pixabaytest.databinding.FragmentHomeFrahmentBinding
import by.kesso.pixabaytest.domain.entity.PixaImage
import by.kesso.pixabaytest.ui.home.adapter.ImageAdapter
import by.kesso.pixabaytest.ui.home.adapter.ImageLoadStateAdapter
import by.kesso.pixabaytest.ui.home.adapter.MarginItemDecoration
import by.kesso.pixabaytest.ui.utils.spanSizeLookup
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment(), ImageAdapter.ClickListener {

    private val viewModel: HomeViewModel by viewModel()
    private val adapter by lazy { ImageAdapter(this) }

    lateinit var binding: FragmentHomeFrahmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home_frahment,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        observeViewModel()
    }

    private fun initView() {
        val spanCount = 3
        val footerAdapter = ImageLoadStateAdapter {adapter.retry()}

        binding.rvList.layoutManager =
            GridLayoutManager(requireContext(), spanCount).spanSizeLookup { position ->
                if (position == adapter.itemCount && footerAdapter.itemCount != 0)
                    spanCount
                else 1
            }
        binding.rvList.addItemDecoration(MarginItemDecoration(10, spanCount))
        binding.rvList.adapter = adapter.withLoadStateFooter(footerAdapter)

        binding.swipeRefresh.setOnRefreshListener {
            adapter.refresh()
        }
    }

    private fun observeViewModel() = with(viewModel) {
        list.observe(viewLifecycleOwner) {
            binding.swipeRefresh.isRefreshing = false
            adapter.submitData(lifecycle, it)
        }
    }

    override fun onClick(image: PixaImage) {
        val direction = HomeFragmentDirections.actionHomeFragmentToImageDetailFragment(image)
        findNavController().navigate(direction)
    }
}