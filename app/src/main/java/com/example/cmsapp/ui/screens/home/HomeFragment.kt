package com.example.cmsapp.ui.screens.home

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.Navigator
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cmsapp.R
import com.example.cmsapp.adapters.UserAdapter
import com.example.cmsapp.databinding.FragmentHomeBinding
import com.example.cmsapp.models.UserDataModelItem
import com.example.cmsapp.networks.UserInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class HomeFragment : Fragment() {
    lateinit var homeBinding: FragmentHomeBinding
    lateinit var linearLayoutManager: LinearLayoutManager

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeBinding = FragmentHomeBinding.inflate(inflater,container,false)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeBinding.fabBtnHome.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }

        displayData()

    }

    private fun displayData() {

        val retrofitBuilder = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://gorest.co.in/public/v2/").build()
            .create(UserInterface::class.java)

        val usersData = retrofitBuilder.getUserData()

        homeBinding.recyclerViewHome.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(activity)
        homeBinding.recyclerViewHome.layoutManager = linearLayoutManager

        usersData.enqueue(object: Callback<List<UserDataModelItem>> {
            override fun onResponse(
                call: Call<List<UserDataModelItem>>,
                response: Response<List<UserDataModelItem>>
            ) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<List<UserDataModelItem>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }


}


