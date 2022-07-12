package com.sajjad.movie_project.ui.search

import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sajjad.movie_project.common.BaseFragment
import com.sajjad.movie_project.databinding.FragmentSearchBinding
import com.sajjad.movie_project.ui.common.MoviesAdapter
import com.sajjad.movie_project.utils.gone
import com.sajjad.movie_project.utils.initRecyclerView
import com.sajjad.movie_project.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    @Inject
    lateinit var adapter: MoviesAdapter

    private val viewModel: SearchViewModel by viewModels()

    override fun initViews() {
        binding.etSearch.addTextChangedListener {
            val search = it.toString()
            if (search.isNotEmpty())
                viewModel.searchMovie(search)
        }

        //Adapter click
        adapter.onItemClickListener {
            val direction = SearchFragmentDirections.actionToDetail(it.id)
            findNavController().navigate(direction)
        }
    }

    override fun observe() {

        viewModel.apply {
            //MoviesList
            moviesLiveMovie.observe(viewLifecycleOwner) {
                adapter.submitList(it)
                initRecyclerView()
            }

            //IsEmpty
            emptyLiveData.observe(viewLifecycleOwner) {
                /*
                * not used showLoadingView() function.
                * because in this fragment we want to show loading view in center
                */
                if (it) {
                    binding.clEmpty.visible()
                    binding.rvMovies.gone()
                } else {
                    binding.clEmpty.gone()
                    binding.rvMovies.visible()
                }

            }

            //IsLoading
            loadingLiveData.observe(viewLifecycleOwner) {
                if (it) {
                    binding.clLoading.visible()
                    binding.clEmpty.gone()
                    binding.rvMovies.gone()
                } else {
                    binding.clLoading.gone()
                }
            }
        }

    }

    override fun callApi() {

    }

    private fun initRecyclerView() {
        binding.rvMovies.initRecyclerView(LinearLayoutManager(requireContext()), adapter)
    }

}