package com.sajjad.movie_project.ui.detail

import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.sajjad.movie_project.R
import com.sajjad.movie_project.common.BaseFragment
import com.sajjad.movie_project.data.db.MovieEntity
import com.sajjad.movie_project.data.response.detail.MovieDetailResponse
import com.sajjad.movie_project.databinding.FragmentDetailBinding
import com.sajjad.movie_project.ui.detail.adapter.ImagesAdapter
import com.sajjad.movie_project.utils.initRecyclerView
import com.sajjad.movie_project.utils.loadImage
import dagger.hilt.android.AndroidEntryPoint
import java.lang.IllegalArgumentException
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {
    @Inject
    lateinit var actorAdapter: ImagesAdapter

    @Inject
    lateinit var entity: MovieEntity

    private val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    override fun initViews() {
        //Show error
        if (args.movieId <= 0) {
            throw IllegalArgumentException("Movie id(${args.movieId}) is incorrect!.")
        }

        binding.apply {
            //Click
            ivBack.setOnClickListener {
                navigateUp()
            }

            ivFav.setOnClickListener {
                viewModel.handleFavoriteMovie(entity)
            }

            //Change default fav color
            lifecycleScope.launchWhenCreated {
                val isExists = viewModel.isEntityExists(args.movieId)
                if (isExists) {
                    ivFav.setColorFilter(ContextCompat.getColor(requireContext(), R.color.scarlet))
                } else {
                    ivFav.setColorFilter(ContextCompat.getColor(requireContext(), R.color.philippineSilver))
                }
            }
        }

        actorAdapter.onItemClickListener {
            val direction = DetailFragmentDirections.actionDetailToImage(it)
            findNavController().navigate(direction)
        }

    }

    override fun observe() {
        viewModel.apply {
            //Detail info
            detailLivedata.observe(viewLifecycleOwner) {
                initData(it)
            }

            isFavorite.observe(viewLifecycleOwner) {
                if (it) {
                    binding.ivFav.setColorFilter(ContextCompat.getColor(requireContext(), R.color.scarlet))
                } else {
                    binding.ivFav.setColorFilter(ContextCompat.getColor(requireContext(), R.color.philippineSilver))
                }
            }

            //Loading
            loadingLivedata.observe(viewLifecycleOwner) {
                showLoadingView(it)
            }
        }

    }

    private fun initData(data: MovieDetailResponse) {
        binding.apply {
            ivMovieBackground.loadImage(data.poster)
            ivMoviePoster.loadImage(data.poster)
            tvRating.text = data.imdbRating
            tvTime.text = data.runtime
            tvYear.text = data.released
            tvSummary.text = data.plot
        }
        actorAdapter.submitList(data.images)
        initActorsRecyclerView()

        //init entity properties
        entity.apply {
            id = args.movieId
            poster = data.poster
            title = data.title
            rate = data.imdbRating
            country = data.country
            year = data.year
        }
    }

    override fun callApi() {
        viewModel.getMovieDetail(args.movieId)
    }

    private fun initActorsRecyclerView() {
        binding.rvActors.initRecyclerView(
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false),
            actorAdapter
        )
    }
}