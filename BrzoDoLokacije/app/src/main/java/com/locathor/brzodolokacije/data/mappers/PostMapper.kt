package com.locathor.brzodolokacije.data.mappers

import com.locathor.brzodolokacije.data.local.post.PostEntity
import com.locathor.brzodolokacije.data.remote.dto.PostDto
import com.locathor.brzodolokacije.domain.model.Post
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun Post.toPostEntity(): PostEntity {
    return PostEntity(
        title = title,
        desc = desc,
        latitude = latitude,
        longitude = longitude,
        createdAt = createdAt
    )
}

fun PostEntity.toPost(): Post {
    return Post(
        title = title,
        desc = desc,
        latitude = latitude,
        longitude = longitude,
        createdAt = createdAt
    )
}

fun PostDto.toPostEntity(): PostEntity {
    return PostEntity(
        title = title ?: "",
        desc = desc ?: "",
        latitude = latitude ?: 0.0,
        longitude = longitude ?: 0.0,
        createdAt = createdAt ?: ""
    )
}

