package com.locathor.brzodolokacije.domain.repository

interface AuthRepository {
    fun refreshToken(refreshToken: String): String
}