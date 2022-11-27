package com.locathor.brzodolokacije.domain.model

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Post(
    var title: String,
    var desc: String,
    var latitude: Double,
    var longitude: Double,
    var createdAt: String,
    val mediaUris: List<String>,
    val ownerUsername: String,
    val likeCount: Int,
    val commentCount: Int
) : Parcelable
