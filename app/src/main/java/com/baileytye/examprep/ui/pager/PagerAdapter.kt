package com.baileytye.examprep.ui.pager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.baileytye.examprep.databinding.ItemPagerBinding

class PagerAdapter :
    ListAdapter<String, PagerAdapter.PagerViewHolder>(StringDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        return PagerViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PagerViewHolder private constructor(val binding: ItemPagerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: String
        ) {
            binding.apply {
                title = item
                executePendingBindings()
            }
        }

        companion object {
            fun from(parent: ViewGroup): PagerViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemPagerBinding.inflate(layoutInflater, parent, false)

                return PagerViewHolder(binding)
            }
        }
    }

    object StringDiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }
}