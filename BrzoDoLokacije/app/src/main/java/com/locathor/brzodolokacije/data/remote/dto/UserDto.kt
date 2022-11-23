package com.locathor.brzodolokacije.data.remote.dto

import com.squareup.moshi.Json

data class UserDto(
    val userId: Int,
    val email: String,
    @field: Json(name = "profilePicId") val profilePicUrl: String,
    val username: String,
    val name: String,
    val surname: String,
    val password: String
)
