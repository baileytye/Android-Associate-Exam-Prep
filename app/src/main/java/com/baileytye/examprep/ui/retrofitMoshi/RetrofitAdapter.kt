package com.baileytye.examprep.ui.retrofitMoshi

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.baileytye.examprep.data.MarsProperty
import com.baileytye.examprep.databinding.ItemRetrofitBinding

class RetrofitAdapter(private val clickListener: ItemClickListener) :
    ListAdapter<MarsProperty, RetrofitAdapter.PropertyViewHolder>(MarsPropertyDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        return PropertyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class PropertyViewHolder private constructor(val binding: ItemRetrofitBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: MarsProperty,
            clickListener: ItemClickListener
        ) {
            binding.apply {
                property = item
                this.clickListener = clickListener
                executePendingBindings()
                ViewCompat.setTransitionName(image, "image_${item.id}")
            }
        }

        companion object {
            fun from(parent: ViewGroup): PropertyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemRetrofitBinding.inflate(layoutInflater, parent, false)

                return PropertyViewHolder(binding)
            }
        }
    }

    object MarsPropertyDiffCallback : DiffUtil.ItemCallback<MarsProperty>() {
        override fun areItemsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
            return oldItem == newItem
        }

    }
}

class ItemClickListener(val clickListener: (item: MarsProperty, imageView: ImageView) -> Unit) {
    fun onClick(item: MarsProperty, imageView: ImageView) = clickListener(item, imageView)
}