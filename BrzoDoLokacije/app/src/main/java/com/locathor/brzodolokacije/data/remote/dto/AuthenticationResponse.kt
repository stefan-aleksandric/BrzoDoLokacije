package com.locathor.brzodolokacije.data.remote.dto

import com.squareup.moshi.Json

data class AuthenticationResponse(
    @field: Json(name="user") val user: UserDto,
    @field: Json(name="token") val authToken: Body
)

data class Body(
    val value: String,
    val expirationDate: String
)