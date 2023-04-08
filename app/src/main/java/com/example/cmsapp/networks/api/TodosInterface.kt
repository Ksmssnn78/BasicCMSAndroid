package com.example.cmsapp.networks.api

import com.example.cmsapp.models.TodoListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TodosInterface {
    @GET("v2/users/{userId}/todos")
    suspend fun getTodos(@Path("userId") userId: Int): Response<List<TodoListItem>>
}
