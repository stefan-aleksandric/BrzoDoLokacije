package com.locathor.brzodolokacije.data.remote

import com.locathor.brzodolokacije.data.remote.dto.*
import retrofit2.http.*
import retrofit2.http.Body

interface UserApi {

    @POST(REGISTER)
    suspend fun registerUser(
        @Body registerRequest: RegisterRequest
    ): AuthenticationResponse


    @POST(LOGIN)
    suspend fun loginUser(
        @Body loginRequest: LoginRequest
    ): AuthenticationResponse

    companion object {
        const val BASE_URL = "http://softeng.pmf.kg.ac.rs:10012"
        const val LOGIN = "api/Authentication/login"
        const val REGISTER = "api/Authentication/register"
    }
}