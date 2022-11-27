package com.locathor.brzodolokacije.data.remote.dto

import com.squareup.moshi.Json

data class CreatePostRequest(
     var title: String,
     var description: String,
     var latitude: Double,
     var longitude: Double,
     var createdDate: String,
     @field: Json(name="photoUrl") val images: String,
     @field: Json(name="username") val ownerUsername: String
)
