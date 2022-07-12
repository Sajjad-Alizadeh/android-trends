package com.sajjad.movie_project.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sajjad.movie_project.data.response.detail.MovieDetailResponse
import com.sajjad.movie_project.data.response.home.GenresListResponse.GenresListResponseItem
import com.sajjad.movie_project.databinding.ItemActorBinding
import com.sajjad.movie_project.databinding.ItemGenreBinding
import javax.inject.Inject

class ImagesAdapter @Inject constructor() : RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {
    private lateinit var binding: ItemActorBinding
    private val differCallBack = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, differCallBack)

    //Click
    private var onItemClickListener: ((String) -> Unit)? = null
    fun onItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemActorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun submitList(newList: List<String>) {
        differ.submitList(newList)
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        fun bind(url: String) {
            binding.apply {
                ivActor.load(url) {
                    crossfade(true)
                    crossfade(800)
                }
                binding.root.setOnClickListener {
                    onItemClickListener?.let {
                        it(url)
                    }
                }
            }
        }
    }

}