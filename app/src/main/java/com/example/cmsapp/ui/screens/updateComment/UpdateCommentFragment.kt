package com.example.cmsapp.ui.screens.updateComment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.cmsapp.R
import com.example.cmsapp.databinding.FragmentUpdateCommentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateCommentFragment : Fragment(R.layout.fragment_update_comment) {
    private lateinit var updateCommentsBinding: FragmentUpdateCommentBinding
    private val args: UpdateCommentFragmentArgs by navArgs()

    private val viewModel: UpdateCommentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        updateCommentsBinding = FragmentUpdateCommentBinding.bind(view)

        updateCommentsBinding.nameTxtInputUpdateComment.setText(args.userName)
        updateCommentsBinding.nameTxtInputUpdateComment.isEnabled = false
        updateCommentsBinding.emailTxtInputUpdateComment.setText(args.mail)
        updateCommentsBinding.emailTxtInputUpdateComment.isEnabled = false
        updateCommentsBinding.bodyTxtInputUpdateComment.hint = args.cmnt
        updateCommentsBinding.updateCommentBtnUpdateComment.setOnClickListener {
            updateComment()
        }
    }

    private fun updateComment() {
        val cmnt = updateCommentsBinding.bodyTxtInputUpdateComment.text.toString()
        viewModel.updateComments(args.commentId, args.userName, args.mail, cmnt,args.postId)
        findNavController().popBackStack()
    }
}
