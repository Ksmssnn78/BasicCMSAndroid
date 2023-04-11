package com.example.cmsapp.networks.api

import com.example.cmsapp.models.CommentListModelItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CommentsInterface {
    @GET("posts/{postId}/comments")
    suspend fun getCommentsDetails(@Path("postId") userId: Int): Response<List<CommentListModelItem>>

    @POST("posts/{postId}/comments")
    suspend fun addComment(
        @Path("postId") postId: Int,
        @Body comment: CommentListModelItem
    ): Response<CommentListModelItem>
}
