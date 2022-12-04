package com.locathor.brzodolokacije.data.remote.dto

data class PostDto(
    val postId: Int,
    val title: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val createdDate: String,
    val photoUrl: String,
    val username: String
)
