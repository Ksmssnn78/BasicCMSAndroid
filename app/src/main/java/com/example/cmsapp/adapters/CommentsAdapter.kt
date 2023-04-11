package com.example.cmsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cmsapp.databinding.IndividualItemCommentsBinding
import com.example.cmsapp.models.CommentListModelItem

class CommentsAdapter :
    ListAdapter<CommentListModelItem, CommentsAdapter.CommentsViewHolder>(
        DIFF_CALLBACK
    ) {

    class CommentsViewHolder(
        private val commentsBinding: IndividualItemCommentsBinding
    ) : RecyclerView.ViewHolder(commentsBinding.root) {
        fun bind(item: CommentListModelItem) {
            commentsBinding.nameTxtComments.setText(item.name)
            commentsBinding.commentTxtComments.setText(item.body)
        }

        companion object {
            fun from(
                parent: ViewGroup
            ): CommentsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = IndividualItemCommentsBinding.inflate(layoutInflater, parent, false)
                return CommentsViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        return CommentsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        val comment = getItem(position)
        holder.bind(comment)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CommentListModelItem>() {
            override fun areItemsTheSame(
                oldItem: CommentListModelItem,
                newItem: CommentListModelItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: CommentListModelItem,
                newItem: CommentListModelItem
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}
