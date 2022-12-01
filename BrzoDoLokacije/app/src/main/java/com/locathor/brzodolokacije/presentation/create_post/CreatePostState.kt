package com.locathor.brzodolokacije.presentation.create_post

import android.location.Location
import android.net.Uri
import com.google.android.gms.maps.model.LatLng
import com.locathor.brzodolokacije.domain.model.Post

data class CreatePostState(
    val description: String = "",
    var mediaUris: List<Uri> = emptyList(),
    val title:String="",
    val location: LatLng? = null,
    val posts: List<Post> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)