package com.locathor.brzodolokacije.data.remote

import com.locathor.brzodolokacije.data.remote.dto.CreatePostRequest
import com.locathor.brzodolokacije.data.remote.dto.PostDto
import com.locathor.brzodolokacije.util.Resource
import retrofit2.http.*

interface PostApi {

    @GET(POST)
    suspend fun getAllPosts(): List<PostDto>


    @GET("${POST}/{id}")
    suspend fun getPostForId(
        @Path("id") postId: Int
    ): PostDto

    @POST(POST)
    suspend fun createPost(
        @Body createPostRequest: CreatePostRequest
    ): Int

    @GET(POST)
    suspend fun getPost(): List<PostDto>

    @GET("${POST}/UserPosts")
    suspend fun getPostsForUsername(@Header("username") username: String): List<PostDto>

    companion object{
        const val BASE_URL = "http://softeng.pmf.kg.ac.rs:10012"
        const val POST = "api/Post"
        const val PAGE = 1
        const val LIMIT = 10
    }
}