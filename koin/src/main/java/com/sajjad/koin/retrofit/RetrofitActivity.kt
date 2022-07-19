package com.sajjad.koin.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sajjad.koin.databinding.ActivityRetrofitBinding
import org.koin.android.ext.android.inject

class RetrofitActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRetrofitBinding

    private val adapter: MoviesAdapter by inject()
    private val viewModel: RetrofitViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetrofitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getMovies()

        binding.apply {
            rvMovie.layoutManager = LinearLayoutManager(this@RetrofitActivity, RecyclerView.VERTICAL, false)
            rvMovie.adapter = adapter
        }

        viewModel.movieLiveData.observe(this) {
            it.data?.let { data ->
                adapter.submitList(data)
            }

        }
    }
}