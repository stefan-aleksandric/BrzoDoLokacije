package com.locathor.brzodolokacije.data.remote.dto

import com.squareup.moshi.Json

data class CreatePostRequest(
     @field: Json(name="postId") var postId: Int = 0,
     var title: String,
     var description: String,
     var latitude: Double,
     var longitude: Double,
     var createdDate: String,
     @field: Json(name="photo_url") val images: String,
     @field: Json(name="username") val ownerUsername: String
)
