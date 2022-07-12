package com.sajjad.movie_project.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sajjad.movie_project.common.BaseFragment
import com.sajjad.movie_project.databinding.FragmentFavoriteBinding
import com.sajjad.movie_project.ui.favorite.adapter.FavoriteAdapter
import com.sajjad.movie_project.utils.gone
import com.sajjad.movie_project.utils.initRecyclerView
import com.sajjad.movie_project.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(FragmentFavoriteBinding::inflate) {
    private val viewModel: FavoriteViewModel by viewModels()

    @Inject
    lateinit var adapter: FavoriteAdapter

    override fun initViews() {
        //Load favorite list
        viewModel.getFavoriteMovies()

        //Adapter click
        adapter.onItemClickListener {
            val direction = FavoriteFragmentDirections.actionToDetail(it.id)
            findNavController().navigate(direction)
        }
    }

    override fun observe() {
        viewModel.apply {
            moviesLiveData.observe(viewLifecycleOwner) {
                adapter.submitList(it)
                initFavoriteMoviesList()
            }

            emptyLiveData.observe(viewLifecycleOwner) {
                showingEmptyView(it)
            }
        }
    }

    override fun callApi() {}

    private fun initFavoriteMoviesList() {
        binding.rvMovies.initRecyclerView(LinearLayoutManager(requireContext()), adapter)
    }

    private fun showingEmptyView(mustShow: Boolean) {
        binding.apply {
            if (mustShow) {
                clEmpty.visible()
                rvMovies.gone()
            } else {
                clEmpty.gone()
                rvMovies.visible()
            }

        }
    }

}