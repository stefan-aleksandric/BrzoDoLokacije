package com.locathor.brzodolokacije.data.remote

import com.locathor.brzodolokacije.data.remote.dto.PostDto
import com.locathor.brzodolokacije.util.Resource
import retrofit2.http.GET

interface PostApi {

    @GET()
    suspend fun getAllPosts(): List<PostDto>

    companion object{
        const val BASE_URL = ""
    }
}