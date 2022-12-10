package com.locathor.brzodolokacije.data.remote.dto

import com.squareup.moshi.Json

data class CreatePostRequest(
     val title: String,
     val description: String,
     val latitude: Double,
     val longitude: Double,
     val createdDate: String,
     @field: Json(name="photoUrl") val images: String,
     @field: Json(name="username") val ownerUsername: String
)
