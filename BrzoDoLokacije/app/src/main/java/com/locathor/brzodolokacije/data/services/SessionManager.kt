package com.locathor.brzodolokacije.data.services

import javax.inject.Inject

class SessionManager @Inject constructor(
    private val pref: AppSharedPreferences
) {
    fun getAccessToken(): String? = pref.getToken()

    fun getRefreshToken(): String? = pref.getRefreshToken()

    fun refreshToken(refreshToken: String): String {
        pref.setRefreshToken(refreshToken)
        return refreshToken
    }//authRepository.refreshToken(refreshToken)

    fun getCurrentUsername(): String? =
        pref.getUser()

    fun setCurrentUsername(username: String) =
        pref.setUser(username)

    fun logout() {
        pref.removeToken()
        pref.removeRefreshToken()
        pref.removeUser()
    }
}