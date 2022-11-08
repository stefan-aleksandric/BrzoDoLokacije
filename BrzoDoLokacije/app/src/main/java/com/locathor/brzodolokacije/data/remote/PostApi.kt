package com.locathor.brzodolokacije.data.remote

import com.locathor.brzodolokacije.data.remote.dto.CreatePostRequest
import com.locathor.brzodolokacije.data.remote.dto.PostDto
import com.locathor.brzodolokacije.util.Resource
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface PostApi {

    @GET("${POSTS_URL}?p=${PAGE}&l=${LIMIT}")
    suspend fun getAllPosts(): List<PostDto>


    @FormUrlEncoded
    @GET(POSTS_URL)
    suspend fun getPostsForId(
        @Field("post_id") postId: Int
    ): PostDto

    @POST
    suspend fun createPost(
        @Body createPostRequest: CreatePostRequest
    ): PostDto

    companion object{
        //const val BASE_URL = "https://6349fbbd33bb42dca4fcbc69.mockapi.io"
        const val BASE_URL = "https://localhost:5001/api/user"
        const val POSTS_URL = "test/user/1/post"
        const val PAGE = 1
        const val LIMIT = 10
    }
}