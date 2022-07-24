package com.sajjad.paging3.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sajjad.paging3.databinding.LayoutLoadingListBinding

class ListLoadingAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<ListLoadingAdapter.ViewHolder>() {
    private lateinit var binding: LayoutLoadingListBinding


    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        binding = LayoutLoadingListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(retry)
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class ViewHolder(retry: () -> Unit) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.btnRetry.setOnClickListener {
                retry()
            }
        }

        fun bind(state: LoadState) {
            binding.pbLoading.isVisible = state is LoadState.Loading
            binding.tvError.isVisible = state is LoadState.Error
            binding.btnRetry.isVisible = state is LoadState.Error
        }
    }
}