package com.example.cmsapp.ui.screens.postAdd

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.cmsapp.R
import com.example.cmsapp.databinding.FragmentAddPostBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddPostFragment : Fragment(R.layout.fragment_add_post) {
    private lateinit var addPostBinding: FragmentAddPostBinding
    private val args: AddPostFragmentArgs by navArgs()
    private val viewModel: AddPostViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        addPostBinding = FragmentAddPostBinding.bind(view)

        addPostBinding.addPostBtnAddPost.setOnClickListener {
            createPost()
        }
    }

    private fun createPost() {
        addPostBinding.postTxtInputAddPost.setSelection(0)
        val title = addPostBinding.titleTxtInputAddPost.text.toString()
        val body = addPostBinding.postTxtInputAddPost.text.toString()
//        Snackbar.make(addPostBinding.addPostBtnAddPost,userId.toString(),Snackbar.LENGTH_SHORT).show()
        viewModel.addPost(userId = args.userId, title = title, body = body)
        findNavController().popBackStack()
    }
}