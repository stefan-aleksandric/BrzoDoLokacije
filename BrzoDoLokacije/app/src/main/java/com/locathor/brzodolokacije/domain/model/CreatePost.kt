package com.locathor.brzodolokacije.domain.model

import android.net.Uri

data class CreatePost(
    var title: String,
    var desc: String,
    var latitude: Double,
    var longitude: Double,
    val mediaUris: List<Uri>,
)
