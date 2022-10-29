package com.locathor.brzodolokacije.data.repository

import com.locathor.brzodolokacije.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
): AuthRepository {
    override fun refreshToken(refreshToken: String): String {
        // do network call and refresh token
        TODO()
    }
}