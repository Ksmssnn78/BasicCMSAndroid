package com.example.cmsapp.ui.screens.profiles

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cmsapp.R
import com.example.cmsapp.adapters.PostAdapter
import com.example.cmsapp.databinding.FragmentProfileBinding
import com.example.cmsapp.interfaces.CommonFunctions
import com.example.cmsapp.models.PostListModelItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile), CommonFunctions {
    private lateinit var profileBinding: FragmentProfileBinding
    private lateinit var postAdapter: PostAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private val viewModel: ProfileViewModel by viewModels()
    private val args: ProfileFragmentArgs by navArgs()

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
            findNavController().navigate(
                ProfileFragmentDirections.actionProfileFragmentToAddPostFragment(
                    args.userId
                )
            )
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
        profileBinding.userIdTxtProfile.text = args.userId.toString()
        viewModel.getPostDetails(args.userId)
    }

    private fun adapterOnClick(post: PostListModelItem) {
        val action =
            ProfileFragmentDirections.actionProfileFragmentToCommentsFragment(post.id, args.userId)
        findNavController().navigate(action)
    }
}
