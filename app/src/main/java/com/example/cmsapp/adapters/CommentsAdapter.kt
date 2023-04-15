package com.example.cmsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cmsapp.databinding.IndividualItemCommentsBinding
import com.example.cmsapp.models.CommentListModelItem

class CommentsAdapter(private val onClick: (name:String, CommentListModelItem) -> Unit) :
    ListAdapter<CommentListModelItem, CommentsAdapter.CommentsViewHolder>(
        DIFF_CALLBACK
    ) {

    class CommentsViewHolder(
        private val commentsBinding: IndividualItemCommentsBinding,
        private val onClick: (name:String, CommentListModelItem) -> Unit
    ) : RecyclerView.ViewHolder(commentsBinding.root) {
        fun bind(item: CommentListModelItem) {
            commentsBinding.nameTxtComments.setText(item.name)
            commentsBinding.commentTxtComments.setText(item.body)

            commentsBinding.deleteBtnComments.setOnClickListener {
                onClick("Delete", item)
            }
            commentsBinding.updateBtnComments.setOnClickListener {
                onClick("Update", item)
            }
        }
        companion object {
            fun from(
                parent: ViewGroup,
                onClick: (name:String, CommentListModelItem) -> Unit
            ): CommentsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = IndividualItemCommentsBinding.inflate(layoutInflater, parent, false)
                return CommentsViewHolder(binding, onClick)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        return CommentsViewHolder.from(parent, onClick)
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
