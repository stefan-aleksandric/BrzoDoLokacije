package com.locathor.brzodolokacije.data.local.post

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class PostEntity(
    val title: String,
    val desc: String,
    val createdAt: String,
    //val images: Array<String>,
    val latitude: Double,
    val longitude: Double,
    @PrimaryKey val id: Int? = null
)