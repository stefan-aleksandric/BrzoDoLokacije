package com.locathor.brzodolokacije.data.mappers

import com.locathor.brzodolokacije.data.local.post.PostEntity
import com.locathor.brzodolokacije.data.remote.dto.CreatePostRequest
import com.locathor.brzodolokacije.data.remote.dto.PostDto
import com.locathor.brzodolokacije.domain.model.Post

fun PostEntity.toPost(): Post {
    return Post(
        title = title,
        desc = desc,
        latitude = latitude,
        longitude = longitude,
        createdAt = createdAt,
        ownerUsername = owner,
        mediaUris = mediaUris,
        commentCount = commentCount,
        likeCount = likeCount
    )
}

fun PostDto.toPostEntity(): PostEntity {
    return PostEntity(
        postId = postId,
        title = title ?: "",
        desc = description ?: "",
        latitude = latitude ?: 0.0,
        longitude = longitude ?: 0.0,
        createdAt = createdDate ?: "",
        mediaUris = listOf(photoUrl),
        commentCount = 0,
        likeCount = 0,
        owner = username
    )
}



