package com.locathor.brzodolokacije.presentation.create_post

import android.net.Uri

data class CreatePostState(
    val description: String = "",
    var mediaUris: List<Uri> = emptyList(),
    val title:String="",
    val error: String = "",
    val location: String = "",
)