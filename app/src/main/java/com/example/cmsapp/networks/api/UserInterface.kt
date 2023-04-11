package com.example.cmsapp.networks.api

import com.example.cmsapp.models.UserDataModelItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserInterface {
    @GET("users")
    suspend fun getUserData(): Response<List<UserDataModelItem>>
    @POST("users")
    suspend fun addUser(@Body user: UserDataModelItem): Response<UserDataModelItem>
}
