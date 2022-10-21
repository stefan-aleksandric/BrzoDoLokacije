package com.locathor.brzodolokacije.data.remote

import com.locathor.brzodolokacije.data.remote.dto.UserDto
import com.locathor.brzodolokacije.domain.model.User
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface BrzoDoLokacijeApi {

    @GET(USERS_URL)
    suspend fun getAllUsers(): List<UserDto>

    //GET JWT

    companion object {
        const val BASE_URL = "https://6349fbbd33bb42dca4fcbc69.mockapi.io"
        const val USERS_URL = "test/user"
    }
}