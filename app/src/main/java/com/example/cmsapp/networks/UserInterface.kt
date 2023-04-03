package com.example.cmsapp.networks

import com.example.cmsapp.models.UserDataModelItem
import retrofit2.Call
import retrofit2.http.GET

interface UserInterface {
    @GET("users")
    fun getUserData():Call<List<UserDataModelItem>>
}