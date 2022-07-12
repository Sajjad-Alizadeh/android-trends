package com.sajjad.hilt.room

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sajjad.hilt.databinding.ItemUserBinding
import com.sajjad.hilt.room.model.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserAdapter @Inject constructor() : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private lateinit var binding: ItemUserBinding

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(user: User) {
            binding.tvUserName.text = "${user.id}--> ${user.name}"
            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(user)
                }
            }
        }
    }

    private val diffCallBack = object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean = oldItem == newItem
    }
    private val differ = AsyncListDiffer(this, diffCallBack)
    fun submitList(users: MutableList<User>) {
        differ.submitList(users)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    //click
    private var onItemClickListener: ((User) -> Unit)? = null

    fun onItemClickListener(listener: (User) -> Unit) {
        onItemClickListener = listener
    }

}