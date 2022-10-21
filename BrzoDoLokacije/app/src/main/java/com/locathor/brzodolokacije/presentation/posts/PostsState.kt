package com.locathor.brzodolokacije.presentation.posts

import com.locathor.brzodolokacije.domain.model.Post
import com.locathor.brzodolokacije.domain.model.User

data class PostsState(
    val posts: List<Post> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)