package com.locathor.brzodolokacije.data.local.post

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class PostEntity(
    val title: String,
    val desc: String,
    val createdAt: String,
    val mediaUris: List<String>,
    val latitude: Double,
    val longitude: Double,
    val owner: String,
    val commentCount: Int,
    val likeCount: Int,
    val userId: Int? = null,
    @PrimaryKey val postId: Int? = null
)