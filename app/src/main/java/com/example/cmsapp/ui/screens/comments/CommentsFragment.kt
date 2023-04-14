package com.example.cmsapp.ui.screens.comments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cmsapp.R
import com.example.cmsapp.adapters.CommentsAdapter
import com.example.cmsapp.databinding.FragmentCommentsBinding
import com.example.cmsapp.interfaces.CommonFunctions
import com.example.cmsapp.models.CommentListModelItem
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentsFragment : Fragment(R.layout.fragment_comments), CommonFunctions {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var commentsBinding: FragmentCommentsBinding
    private lateinit var commentsAdapter: CommentsAdapter
    private val viewModel: CommentsViewModel by viewModels()
    private val args: CommentsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()
        commentsAdapter = CommentsAdapter{comment->
//            Toast.makeText(this.requireContext(),comment.id.toString(),Toast.LENGTH_LONG).show()
            adapterOnClick(comment)
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        commentsBinding = FragmentCommentsBinding.bind(view)
        commentsBinding.fabBtnComment.setOnClickListener {
            findNavController().navigate(
                CommentsFragmentDirections.actionCommentsFragmentToAddCommentsFragment(
                    args.postId
                )
            )
        }
        loadData()
    }

    override fun initObservers() {
        viewModel.commentInfo.observe(this) { comments ->
            commentsAdapter.submitList(comments)
        }
    }

    override fun loadData() {
        commentsBinding.recyclerViewComments.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(activity)
        commentsBinding.recyclerViewComments.layoutManager = linearLayoutManager
        commentsBinding.recyclerViewComments.adapter = commentsAdapter

        commentsBinding.postIdTxtComments.setText(args.postId.toString())
        commentsBinding.userIdTxtComments.setText(args.userId.toString())
        viewModel.getComments(args.postId)
    }
    private fun adapterOnClick(comment: CommentListModelItem) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Wants to delete this comment?")
            .setNegativeButton("Decline",null)
            .setPositiveButton("YES"){_,_->
                viewModel.deleteComment(comment.id)
            }.show()

    }
}
