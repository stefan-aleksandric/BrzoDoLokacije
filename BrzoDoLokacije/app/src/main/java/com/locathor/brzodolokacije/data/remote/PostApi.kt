package com.locathor.brzodolokacije.data.remote

import com.locathor.brzodolokacije.data.remote.dto.CreatePostRequest
import com.locathor.brzodolokacije.data.remote.dto.PostDto
import com.locathor.brzodolokacije.util.Resource
import retrofit2.http.*

interface PostApi {

    @GET
    suspend fun getAllPosts(): List<PostDto>


    @GET("{id}")
    suspend fun getPostsForId(
        @Path("id") postId: Int
    ): PostDto

    @POST
    suspend fun createPost(
        @Body createPostRequest: CreatePostRequest
    ): PostDto

    companion object{
        const val BASE_URL = "http://softeng.pmf.kg.ac.rs:10012/api/Post"
        const val PAGE = 1
        const val LIMIT = 10
    }
}