package com.sajjad.hilt.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.sajjad.hilt.databinding.ActivityRetrofitBinding
import com.sajjad.hilt.retrofit.model.MoviesResponse
import com.sajjad.hilt.retrofit.repository.RetrofitRepository
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@AndroidEntryPoint
class RetrofitActivity : AppCompatActivity() {
    private val TAG = "RetrofitActivity"
    private lateinit var binding: ActivityRetrofitBinding

    @Inject
    lateinit var moviesAdapter: MoviesAdapter

    @Inject
    lateinit var repository: RetrofitRepository

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetrofitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        moviesAdapter.submitList(emptyList())

        binding.apply {
            //Init rv
            rvMovie.apply {
                layoutManager = LinearLayoutManager(this@RetrofitActivity)
                adapter = moviesAdapter
            }
        }

        getDataFromServer()

    }
    private fun getDataFromServer() {
        showLoadingView(true)
        repository.getMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                showLoadingView(false)
            }
            .subscribe(object : SingleObserver<MoviesResponse> {
                override fun onSubscribe(d: Disposable) {
                    Log.d(TAG, "onSubscribe called()")
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: MoviesResponse) {
                    Log.d(TAG, "onSuccess called()")
                    t.data?.let { movies ->
                        moviesAdapter.submitList(movies)
                    }
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "onError called()")

                }

            })
    }

    private fun showLoadingView(mustShow: Boolean) {
        if (mustShow) {
            binding.pbMovie.visibility = View.VISIBLE
        } else
            binding.pbMovie.visibility = View.GONE
    }
}