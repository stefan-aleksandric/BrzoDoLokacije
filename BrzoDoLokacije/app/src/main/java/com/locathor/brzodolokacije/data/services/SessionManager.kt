package com.locathor.brzodolokacije.data.services

import com.locathor.brzodolokacije.domain.repository.AuthRepository
import javax.inject.Inject

class SessionManager @Inject constructor(
    private val pref: AppSharedPreferences,
    private val authRepository: AuthRepository
) {

    fun getAccessToken(): String? = pref.getToken()

    fun getRefreshToken(): String? = pref.getRefreshToken()

    fun refreshToken(refreshToken: String): String {
        pref.setRefreshToken(refreshToken)
        return refreshToken
    }//authRepository.refreshToken(refreshToken)

    fun logout() {
        /* .... */
        pref.setToken("")
    }
}