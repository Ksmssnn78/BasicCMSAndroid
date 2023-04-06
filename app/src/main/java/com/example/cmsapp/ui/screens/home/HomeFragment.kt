package com.example.cmsapp.ui.screens.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

@AndroidEntryPoint
class HomeFragment : Fragment() {
    lateinit var homeBinding: FragmentHomeBinding
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var userAdapter: UserAdapter

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

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

        usersData.enqueue(object : Callback<List<UserDataModelItem>> {
            override fun onResponse(
                call: Call<List<UserDataModelItem>>,
                response: Response<List<UserDataModelItem>>,
            ) {
                val userInfo = response.body()
                Log.d("home", userInfo.toString())
                userAdapter = UserAdapter(context!!, userInfo!!)
                userAdapter.notifyDataSetChanged()
                homeBinding.recyclerViewHome.adapter = userAdapter

                userAdapter.setOnItemClickListener(object : UserAdapter.OnItemClickListener {
                    override fun onItemClick(position: Int) {
                        var bundle = Bundle()
                        bundle.putInt("userId", userInfo[position].id)
                        bundle.putString("userName", userInfo[position].name)
                        bundle.putString("status", userInfo[position].status)
                        bundle.putString("gender", userInfo[position].gender)
                        findNavController().navigate(
                            R.id.action_homeFragment_to_profileFragment,
                            bundle,
                        )
                    }
                })
            }

            override fun onFailure(call: Call<List<UserDataModelItem>>, t: Throwable) {
            }
        })
    }
}
