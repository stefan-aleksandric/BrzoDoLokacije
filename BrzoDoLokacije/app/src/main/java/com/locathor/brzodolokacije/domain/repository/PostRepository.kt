package com.locathor.brzodolokacije.domain.repository

import com.locathor.brzodolokacije.domain.model.Post
import com.locathor.brzodolokacije.util.Resource
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    suspend fun getPosts(
        fetchFromRemote: Boolean
    ): Flow<Resource<List<Post>>>

    suspend fun getPost(
        id: Int,
        fetchFromRemote: Boolean
    ): Flow<Resource<List<Post>>>

    suspend fun createPost(
        post: Post
    ): Flow<Resource<Post>>
}