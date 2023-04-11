package com.example.cmsapp.ui.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cmsapp.R
import com.example.cmsapp.adapters.UserAdapter
import com.example.cmsapp.databinding.FragmentHomeBinding
import com.example.cmsapp.models.UserDataModelItem
import com.example.cmsapp.networks.ApiClient
import com.example.cmsapp.networks.ApiException
import com.example.cmsapp.networks.api.UserInterface
import com.example.cmsapp.repositories.UserRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    lateinit var homeBinding: FragmentHomeBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var userAdapter: UserAdapter


    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        displayData()
        userAdapter = UserAdapter { user ->
            adapterOnClick(user)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        homeBinding = FragmentHomeBinding.bind(view)
        homeBinding.fabBtnHome.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addUserFragment)
        }

        load()

    }

    private fun load() {
        homeBinding.recyclerViewHome.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(activity)
        homeBinding.recyclerViewHome.layoutManager = linearLayoutManager
        homeBinding.recyclerViewHome.adapter = userAdapter
        viewModel.getDetails()
//        val retrofitBuilder = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create())
//            .baseUrl("https://gorest.co.in/public/v2/").build()
//            .create(UserInterface::class.java)
//
//        val userData = retrofitBuilder.getDataUsers()
//
//        homeBinding.recyclerViewHome.setHasFixedSize(true)
//        linearLayoutManager = LinearLayoutManager(activity)
//        homeBinding.recyclerViewHome.layoutManager = linearLayoutManager
//
//        userData.enqueue(object : Callback<List<UserDataModelItem>?>{
//            override fun onResponse(
//                call: Call<List<UserDataModelItem>?>,
//                response: Response<List<UserDataModelItem>?>
//            ) {
//                val myData = response.body()!!
//                userAdapter.submitList(myData)
//                userAdapter.notifyDataSetChanged()
//                homeBinding.recyclerViewHome.adapter = userAdapter
//            }
//
//            override fun onFailure(call: Call<List<UserDataModelItem>?>, t: Throwable) {
//
//            }
//        })
    }

    private fun displayData() {
        viewModel.userInfo.observe(this) { userInfo ->
            userAdapter.submitList(userInfo)
        }
    }
    private fun adapterOnClick(user:UserDataModelItem) {
        val bundle = Bundle()
        bundle.putInt("userId",user.id)
        findNavController().navigate(R.id.action_homeFragment_to_profileFragment, bundle)
    }
}
