package com.baileytye.examprep.ui.userList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.baileytye.examprep.data.User
import com.baileytye.examprep.databinding.ItemUserBinding

class UserListAdapter(
    private val clickListener: UserItemListener,
    val swipeListener: UserItemSwipeListener
) :
    ListAdapter<User, UserListAdapter.UserViewHolder>(UserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class UserViewHolder private constructor(val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: User,
            clickListener: UserItemListener
        ) {
            binding.apply {
                user = item
                this.clickListener = clickListener
                executePendingBindings()
            }
        }

        companion object {
            fun from(parent: ViewGroup): UserViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemUserBinding.inflate(layoutInflater, parent, false)

                return UserViewHolder(binding)
            }
        }
    }

    class UserDiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return (oldItem == newItem)
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return (oldItem == newItem)
        }

    }
}

class UserItemListener(val clickListener: (user: User) -> Unit) {
    fun onClick(user: User) = clickListener(user)
}

class UserItemSwipeListener(val swipeListener: (user: User) -> Unit) {
    fun onSwipe(user: User) = swipeListener(user)
}