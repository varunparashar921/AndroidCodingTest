package com.utc.androidcodingtest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.utc.androidcodingtest.data.User
import com.utc.androidcodingtest.databinding.UserListItemBinding

class UsersAdapter: PagedListAdapter<User, UsersAdapter.ItemViewHolder>(UserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(UserListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: UsersAdapter.ItemViewHolder, position: Int) {
        val user = getItem(position) as User
        holder.apply {
            bind(user)
            itemView.tag = user
        }
    }

    class ItemViewHolder(private val binding:UserListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: User) {
            binding.apply {
                user = item
                executePendingBindings()
            }
        }
    }

}

private class UserDiffCallback : DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}