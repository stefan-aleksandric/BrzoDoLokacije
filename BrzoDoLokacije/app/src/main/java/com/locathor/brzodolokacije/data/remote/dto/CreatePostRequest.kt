package com.locathor.brzodolokacije.data.remote.dto

data class CreatePostRequest(
     var title: String,
     var desc: String,
     var latitude: Double,
     var longitude: Double,
     var createdAt: String,
     //val image: String,
     val ownerUsername: String
)
