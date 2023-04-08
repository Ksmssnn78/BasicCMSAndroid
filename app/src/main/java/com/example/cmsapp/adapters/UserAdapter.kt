package com.example.cmsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cmsapp.databinding.IndividualItemHomeBinding
import com.example.cmsapp.models.UserDataModelItem

class UserAdapter(private val onClick: (UserDataModelItem) -> Unit) :
    ListAdapter<UserDataModelItem, UserAdapter.UserViewHolder>(
        DIFF_CALLBACK,
    ) {

    class UserViewHolder(
        private val binding: IndividualItemHomeBinding,
        private val onClick: (UserDataModelItem) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserDataModelItem) {
            binding.idHomeRecycler.text = item.id.toString()
            binding.nameHomeRecycler.text = item.name
            binding.emailHomeRecycler.text = item.email
            binding.genderHomeRecycler.text = item.gender

            binding.root.setOnClickListener {
                onClick(item)
            }
        }

        companion object {
            fun from(
                parent: ViewGroup,
                onClick: (UserDataModelItem) -> Unit,
            ): UserViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = IndividualItemHomeBinding.inflate(layoutInflater, parent, false)
                return UserViewHolder(binding, onClick)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.from(parent, onClick)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserDataModelItem>() {
            override fun areItemsTheSame(
                oldItem: UserDataModelItem,
                newItem: UserDataModelItem,
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: UserDataModelItem,
                newItem: UserDataModelItem,
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}
