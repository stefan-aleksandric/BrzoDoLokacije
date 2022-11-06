package com.locathor.brzodolokacije.presentation.posts

import android.location.Location
import com.locathor.brzodolokacije.domain.model.Post
import com.locathor.brzodolokacije.domain.model.User

data class PostsState(
    val location: Location? = null,
    val posts: List<Post> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)