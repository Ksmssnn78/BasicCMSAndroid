package com.example.cmsapp.networks.api

import com.example.cmsapp.models.PostListModelItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PostInterface {
    @GET("users/{userId}/posts")
    suspend fun getPostDetails(@Path("userId") userId: Int): Response<List<PostListModelItem>>

    @POST("users/{userId}/posts")
    suspend fun addPost(
        @Path("userId") userId: Int,
        @Body post: PostListModelItem
    ): Response<PostListModelItem>
}
