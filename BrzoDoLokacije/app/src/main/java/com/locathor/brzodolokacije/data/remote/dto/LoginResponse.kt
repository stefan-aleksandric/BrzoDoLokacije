package com.locathor.brzodolokacije.data.remote.dto

import com.squareup.moshi.Json

data class LoginResponse(
//    @field: Json(name="token") val statusCode: Int,
    @field: Json(name="token") val authToken: Body
)

class Body(
    val value: String,
    val expirationDate: String
)