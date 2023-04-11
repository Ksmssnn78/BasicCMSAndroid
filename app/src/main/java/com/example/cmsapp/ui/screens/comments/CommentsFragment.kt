package com.example.cmsapp.ui.screens.comments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cmsapp.R
import com.example.cmsapp.adapters.CommentsAdapter
import com.example.cmsapp.databinding.FragmentCommentsBinding
import com.example.cmsapp.interfaces.CommonFunctions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentsFragment : Fragment(R.layout.fragment_comments), CommonFunctions {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var commentsBinding: FragmentCommentsBinding
    private lateinit var commentsAdapter: CommentsAdapter
    private val viewModel: CommentsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()
        commentsAdapter = CommentsAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        commentsBinding = FragmentCommentsBinding.bind(view)
        commentsBinding.fabBtnComment.setOnClickListener {
            var bundle = Bundle()
            arguments?.let { it1 -> bundle.putInt("postId", it1.getInt("postId")) }
            findNavController().navigate(R.id.action_commentsFragment_to_addCommentsFragment,bundle)
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
        arguments?.let {
            commentsBinding.postIdTxtComments.setText(it.getInt("postId").toString())
            commentsBinding.userIdTxtComments.setText(it.getInt("usersId").toString())
            viewModel.getComments(it.getInt("postId"))
        }
    }
}
