package com.locathor.brzodolokacije.data.remote.dto

import com.squareup.moshi.Json

data class CreatePostRequest(
     @field: Json(name="postId") val postId: Int = 0,
     val title: String,
     val description: String,
     val latitude: Double,
     val longitude: Double,
     val createdDate: String,
     @field: Json(name="photoUrl") val images: String,
     @field: Json(name="username") val ownerUsername: String
)
