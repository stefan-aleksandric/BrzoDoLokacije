package com.locathor.brzodolokacije.presentation.create_post

import android.location.Location
import com.locathor.brzodolokacije.domain.model.Post

data class CreatePostState(
    val description: String = "",
    val title:String="",
    val error: String = "",
    val location: String = "",
    val post: Post ?= null
)