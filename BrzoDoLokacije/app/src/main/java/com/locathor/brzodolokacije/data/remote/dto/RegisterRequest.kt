package com.locathor.brzodolokacije.data.remote.dto

import retrofit2.http.Field

data class RegisterRequest(
    val username: String,
    val name: String,
    val surname: String,
    val profilePic: String,
    val password: String,
    val email: String
)
