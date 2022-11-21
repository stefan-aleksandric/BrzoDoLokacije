package com.locathor.brzodolokacije.data.remote.dto

data class PostDto(
    val postId: Int,
    val userId: Int,
    val createdAt: String,
    val title: String,
    val desc: String,
    val latitude: Double,
    val longitude: Double,
    val owner: String,
    val mediaUris: List<String>,
    val commentCount: Int,
    val likeCount: Int
)
