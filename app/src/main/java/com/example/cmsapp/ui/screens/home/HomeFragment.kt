package com.example.cmsapp.ui.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cmsapp.R
import com.example.cmsapp.adapters.UserAdapter
import com.example.cmsapp.databinding.FragmentHomeBinding
import com.example.cmsapp.models.UserDataModelItem
import com.example.cmsapp.networks.api.UserInterface
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment() {
    lateinit var homeBinding: FragmentHomeBinding
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var userAdapter: UserAdapter


    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeBinding.fabBtnHome.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }

        load()
        displayData()
        userAdapter = UserAdapter { user ->
            adapterOnClick(user)
        }
    }

    private fun load() {
        viewModel.getDetails()
    }

    private fun displayData() {
        homeBinding.recyclerViewHome.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(activity)
        homeBinding.recyclerViewHome.layoutManager = linearLayoutManager

        viewModel.userInfo.observe(viewLifecycleOwner) { userInfo ->
            userAdapter.submitList(userInfo)
        }
    }
    private fun adapterOnClick(user:UserDataModelItem) {
        findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
    }
}
