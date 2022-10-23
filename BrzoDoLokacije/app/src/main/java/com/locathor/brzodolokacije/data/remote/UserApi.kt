package com.locathor.brzodolokacije.data.remote

import com.locathor.brzodolokacije.data.remote.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {

    @POST(USERS_URL)
    suspend fun registerUser(
        username: String,
        email: String,
        name: String,
        surname: String,
        //profilePic: String,
        password: String
    ): UserDto
    //GET JWT

    companion object {
        const val BASE_URL = "https://6349fbbd33bb42dca4fcbc69.mockapi.io"
        const val USERS_URL = "test/user"
    }
}