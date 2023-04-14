package com.example.cmsapp.ui.screens.addComments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.cmsapp.R
import com.example.cmsapp.databinding.FragmentAddCommentsBinding
import com.example.cmsapp.databinding.FragmentCommentsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class addCommentsFragment : Fragment(R.layout.fragment_add_comments) {

    private lateinit var addCommentsBinding: FragmentAddCommentsBinding

    private val viewModel: AddCommentsViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        addCommentsBinding = FragmentAddCommentsBinding.bind(view)

        addCommentsBinding.addCommentBtnAddComment.setOnClickListener {
            addComment()
        }
    }

    private fun addComment() {
        var mail = addCommentsBinding.emailTxtInputAddComment.text.toString()
        var name = addCommentsBinding.nameTxtInputAddComment.text.toString()
        var body = addCommentsBinding.bodyTxtInputAddComment.text.toString()
        var postId = arguments?.getInt("postId").toString().toInt()
        viewModel.addComment(postId = postId, email = mail, name = name, body = body)
        findNavController().popBackStack()
    }

}