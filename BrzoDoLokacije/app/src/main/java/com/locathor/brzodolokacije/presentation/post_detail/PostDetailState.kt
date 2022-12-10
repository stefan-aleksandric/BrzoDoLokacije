package com.locathor.brzodolokacije.presentation.post_detail

data class PostDetailState(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val addressText: String = ""
)