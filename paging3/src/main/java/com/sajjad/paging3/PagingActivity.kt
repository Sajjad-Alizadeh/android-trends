package com.sajjad.paging3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.sajjad.paging3.databinding.ActivityPagingBinding
import com.sajjad.paging3.paging.ListLoadingAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PagingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPagingBinding

    private val viewModel: PagingViewModel by viewModels()

    @Inject
    lateinit var mAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPagingBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {

            //set data to adapter
            lifecycleScope.launchWhenCreated {
                viewModel.movieList.collect {
                    mAdapter.submitData(it)
                }
            }

            //show loading first page
            lifecycleScope.launchWhenCreated {
                mAdapter.loadStateFlow.collect {
                    val state = it.refresh
                    progressBar.isVisible = state is LoadState.Loading
                }
            }

            //init recyclerView
            rvMovie.apply {
                layoutManager = LinearLayoutManager(this@PagingActivity)
                adapter = mAdapter
            }

            //swipe to refresh
            srlMovies.setOnRefreshListener {
                srlMovies.isRefreshing = false
                mAdapter.refresh()
            }

            //list error handling
            rvMovie.adapter = mAdapter.withLoadStateFooter(
                ListLoadingAdapter { mAdapter.retry() }
            )
        }
    }
}