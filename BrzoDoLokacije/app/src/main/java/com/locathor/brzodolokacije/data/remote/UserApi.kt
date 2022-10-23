package com.locathor.brzodolokacije.data.remote

import com.locathor.brzodolokacije.data.remote.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Field

interface UserApi {

    @POST(USERS_URL)
    suspend fun registerUser(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("name") name: String,
        @Field("surname") surname: String,
        //profilePic: String,
        @Field("password") password: String
    ): UserDto
    //GET JWT

    companion object {
        const val BASE_URL = "https://6349fbbd33bb42dca4fcbc69.mockapi.io"
        const val USERS_URL = "test/user"
    }
}