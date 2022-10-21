package com.locathor.brzodolokacije.data.remote.dto

data class PostDto(
    val id: Int?,
    val title: String?,
    val desc: String?,
    val images: List<String>?,
    val latitude: Double?,
    val longitude: Double?
)
