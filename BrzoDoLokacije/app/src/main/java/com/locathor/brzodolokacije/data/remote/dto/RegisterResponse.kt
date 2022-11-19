package com.locathor.brzodolokacije.data.remote.dto

import com.squareup.moshi.Json

data class RegisterResponse(
    @field: Json(name="jwt") val jwt: String
)
