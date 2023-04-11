package com.example.cmsapp.ui.screens.profiles

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cmsapp.R
import com.example.cmsapp.adapters.PostAdapter
import com.example.cmsapp.databinding.FragmentProfileBinding
import com.example.cmsapp.interfaces.CommonFunctions
import com.example.cmsapp.models.PostListModelItem
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile), CommonFunctions {
    private lateinit var profileBinding: FragmentProfileBinding
    private lateinit var postAdapter: PostAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initObservers()

        postAdapter = PostAdapter { post ->
            adapterOnClick(post)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        profileBinding = FragmentProfileBinding.bind(view)
        profileBinding.fabBtnProfile.setOnClickListener {
            val bundle = Bundle()
            arguments?.let { bundle.putInt("usersId", it.getInt("userId")) }
            findNavController().navigate(R.id.action_profileFragment_to_addPostFragment,bundle)
        }

        loadData()
    }

    override fun initObservers() {
        viewModel.postInfo.observe(this) { postInfo ->
            postAdapter.submitList(postInfo)
        }
    }

    override fun loadData() {
        profileBinding.recyclerViewProfile.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(activity)
        profileBinding.recyclerViewProfile.layoutManager = linearLayoutManager
        profileBinding.recyclerViewProfile.adapter = postAdapter
        profileBinding.userIdTxtProfile.text = arguments?.getInt("userId").toString()
        arguments?.let { viewModel.getPostDetails(it.getInt("userId")) }
    }

    private fun adapterOnClick(post: PostListModelItem) {
        val bundle = Bundle()
        bundle.putInt("postId",post.id)
        arguments?.let { bundle.putInt("usersId", it.getInt("userId")) }
        findNavController().navigate(R.id.action_profileFragment_to_commentsFragment,bundle)
    }
}
