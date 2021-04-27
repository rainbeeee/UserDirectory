package com.rbnb.userdirectory.user_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rbnb.userdirectory.databinding.ItemUserBinding

class UserListAdapter(private val clickListener: UserItemListener) : ListAdapter<User, RecyclerView.ViewHolder>(UserItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is UserViewHolder) {
            val user = getItem(position)
            holder.bind(user, clickListener)
        }
    }
}

class UserViewHolder constructor(private val binding: ItemUserBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {

        fun from(parent: ViewGroup): UserViewHolder {
            val binding = ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return UserViewHolder(binding)
        }
    }

    fun bind(user: User, clickListener: UserItemListener) {
        binding.user = user
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }
}

class UserItemDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}

class UserItemListener(val clickListener: (user: User) -> Unit) {
    fun onClick(user: User) = clickListener(user)
}
