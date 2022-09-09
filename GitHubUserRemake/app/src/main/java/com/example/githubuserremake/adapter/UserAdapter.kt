package com.example.githubuserremake.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuserremake.databinding.UserMainBinding
import com.example.githubuserremake.datasource.UserResponse

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private val listUser = ArrayList<UserResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val userBinding =
            UserMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(userBinding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(listUser[position])
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listUser[position]) }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setListUser(items: ArrayList<UserResponse>) {
        listUser.clear()
        listUser.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount() = listUser.size

    class UserViewHolder(private var binding: UserMainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(userResponse: UserResponse) {
            binding.tvName.text = userResponse.login
            Glide.with(binding.root)
                .load(userResponse.avatarUrl)
                .circleCrop()
                .into(binding.imgAvatar)
        }
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: UserResponse)
    }
}