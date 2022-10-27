package com.locathor.brzodolokacije.data.remote.dto

import com.squareup.moshi.Json

data class LoginResponse(
    @field: Json(name="id") val statusCode: Int,
    @field: Json(name="jwt") val authToken: String,
)