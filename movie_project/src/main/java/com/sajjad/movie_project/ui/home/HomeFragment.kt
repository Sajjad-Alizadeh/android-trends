package com.sajjad.movie_project.ui.home

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.sajjad.movie_project.common.BaseFragment
import com.sajjad.movie_project.databinding.FragmentHomeBinding
import com.sajjad.movie_project.ui.common.MoviesAdapter
import com.sajjad.movie_project.ui.home.adapter.GenresAdapter
import com.sajjad.movie_project.ui.home.adapter.TopMovieAdapter
import com.sajjad.movie_project.utils.initRecyclerView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var topMoviesAdapter: TopMovieAdapter

    @Inject
    lateinit var genresAdapter: GenresAdapter

    @Inject
    lateinit var lastMoviesAdapter: MoviesAdapter

    private val snapHelper: PagerSnapHelper by lazy { PagerSnapHelper() }

    override fun callApi() {
        viewModel.apply {
            //Top movies list request
            getTopMovies(1, 0)

            //Genres list request
            getGenres()

            getLastMovies(1)
        }
    }

    override fun initViews() {
        //Adapter click
        lastMoviesAdapter.onItemClickListener {
            val direction = HomeFragmentDirections.actionToDetail(it.id)
            findNavController().navigate(direction)
        }

    }

    override fun observe() {
        viewModel.apply {
            //TopMovies response
            topMoviesLiveData.observe(viewLifecycleOwner) {
                topMoviesAdapter.submitList(it.data)
                initTopMoviesList()
            }

            //Genres response
            genresLiveData.observe(viewLifecycleOwner) {
                genresAdapter.submitList(it)
                initGenresList()
            }

            //LastMovies response
            lastMoviesLiveData.observe(viewLifecycleOwner) {
                lastMoviesAdapter.submitList(it.data)
                initLastMovieSList()
            }

            //Loading
            progressLiveData.observe(viewLifecycleOwner) {
            }
        }
    }

    private fun initTopMoviesList() {
        binding.apply {
            rvTopMovie.initRecyclerView(
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false),
                topMoviesAdapter
            )
            snapHelper.attachToRecyclerView(rvTopMovie)
            ciTopMovie.attachToRecyclerView(rvTopMovie, snapHelper)
        }
    }

    private fun initGenresList() {
        binding.apply {
            rvGenre.initRecyclerView(
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false),
                genresAdapter
            )
        }
    }

    private fun initLastMovieSList() {
        binding.apply {
            rvLastMovies.initRecyclerView(
                LinearLayoutManager(requireContext()),
                lastMoviesAdapter
            )
        }
    }
}