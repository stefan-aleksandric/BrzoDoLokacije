package com.locathor.brzodolokacije.data.remote

import com.locathor.brzodolokacije.data.remote.dto.LoginResponse
import com.locathor.brzodolokacije.data.remote.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded

interface UserApi {
    @FormUrlEncoded
    @POST(USERS_URL)
    suspend fun registerUser(
        @Field("username") username: String,
        @Field("name") name: String,
        @Field("surname") surname: String,
        //profilePic: String,
        @Field("password") password: String,
        @Field("email") email: String
    ): UserDto
    //GET JWT

    @FormUrlEncoded
    @POST("test/login")
    suspend fun loginUser(
        @Field("username") username: String,
        @Field("password") password: String
    ): LoginResponse

    companion object {
        const val BASE_URL = "https://6349fbbd33bb42dca4fcbc69.mockapi.io"
        //const val BASE_URL = "https://localhost:5001/api/user"
        const val USERS_URL = "test/user"
    }
}