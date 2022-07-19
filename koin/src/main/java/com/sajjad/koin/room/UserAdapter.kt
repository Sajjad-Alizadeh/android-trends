package com.sajjad.koin.room

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sajjad.koin.data.UserModel
import com.sajjad.koin.databinding.ItemUserBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private lateinit var binding: ItemUserBinding

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(userModel: UserModel) {
            binding.tvUserInfo.text = "${userModel.id}--> ${userModel.fullName} ${userModel.age}"
            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(userModel)
                }
            }
        }
    }

    private val diffCallBack = object : DiffUtil.ItemCallback<UserModel>() {
        override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean = oldItem == newItem
    }
    private val differ = AsyncListDiffer(this, diffCallBack)
    fun submitList(userModels: List<UserModel>) {
        differ.submitList(userModels)
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
    private var onItemClickListener: ((UserModel) -> Unit)? = null

    fun onItemClickListener(listener: (UserModel) -> Unit) {
        onItemClickListener = listener
    }

}