package com.locathor.brzodolokacije.data.remote.dto

import com.squareup.moshi.Json

data class RegisterRequest(
    val username: String,
    val name: String,
    val surname: String,
    @field: Json(name="profilePicId") val profilePic: String,
    val password: String,
    val email: String
)
