package com.locathor.brzodolokacije.data.remote

import com.locathor.brzodolokacije.data.remote.dto.UserDto
import retrofit2.http.GET

interface UserApi {

    @GET("${USERS_URL}?p=${PAGE}&l=${LIMIT}")
    suspend fun getAllUsers(): List<UserDto>

    //GET JWT

    companion object {
        const val BASE_URL = "https://6349fbbd33bb42dca4fcbc69.mockapi.io"
        const val USERS_URL = "test/user"
        const val PAGE = 1
        const val LIMIT = 10
    }
}