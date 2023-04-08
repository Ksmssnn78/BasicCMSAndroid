package com.example.cmsapp.networks.api

import com.example.cmsapp.models.UserDataModelItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface UserInterface {
    @GET("users")
    fun getUserData(): Response<List<UserDataModelItem>>
}
