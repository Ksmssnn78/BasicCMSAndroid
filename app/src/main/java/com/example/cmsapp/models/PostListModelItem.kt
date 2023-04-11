package com.example.cmsapp.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostListModelItem(
    @Json(name = "body")
    val body: String,
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "title")
    val title: String,
    @Json(name = "user_id")
    val userId: Int = 1
)