package com.example.cmsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cmsapp.databinding.IndividualItemHomeBinding
import com.example.cmsapp.databinding.IndividualItemProfileBinding
import com.example.cmsapp.models.PostListModelItem
import com.example.cmsapp.models.UserDataModelItem

class PostAdapter(private val onClick: (PostListModelItem) -> Unit) :
    ListAdapter<PostListModelItem, PostAdapter.PostViewHolder>(
        DIFF_CALLBACK,
    ) {

    class PostViewHolder(
        private val postBinding: IndividualItemProfileBinding,
        private val onClick: (PostListModelItem) -> Unit
    ) : RecyclerView.ViewHolder(postBinding.root) {

        fun bind(item: PostListModelItem) {
            postBinding.postIdTxtProfile.text = item.id.toString()
            postBinding.postTitleTxtProfile.text = item.title
            postBinding.postTxtProfile.text = item.body

            postBinding.root.setOnClickListener {
                onClick(item)
            }
        }

        companion object {
            fun from(
                parent: ViewGroup,
                onClick: (PostListModelItem) -> Unit
            ): PostViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = IndividualItemProfileBinding.inflate(layoutInflater, parent, false)
                return PostViewHolder(binding, onClick)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder.from(parent, onClick)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PostListModelItem>() {
            override fun areItemsTheSame(
                oldItem: PostListModelItem,
                newItem: PostListModelItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: PostListModelItem,
                newItem: PostListModelItem
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}
