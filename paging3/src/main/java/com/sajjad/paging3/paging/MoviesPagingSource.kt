package com.sajjad.paging3.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sajjad.paging3.data.MoviesResponse
import com.sajjad.paging3.data.repo.Repository
import javax.inject.Inject

class MoviesPagingSource @Inject constructor(private val repository: Repository) :
    PagingSource<Int, MoviesResponse.Data>() {
    override fun getRefreshKey(state: PagingState<Int, MoviesResponse.Data>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesResponse.Data> {
        return try {
            val currentPage = params.key ?: 1
            val response = repository.getMovie(currentPage)
            val data = response.body()?.data ?: emptyList()
            val responseData = mutableListOf<MoviesResponse.Data>()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}