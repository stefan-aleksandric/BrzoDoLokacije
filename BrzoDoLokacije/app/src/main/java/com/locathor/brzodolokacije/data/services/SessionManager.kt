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

    fun getCurrentUserId(id: Int) =
        pref.getUserId(id)

    fun setCurrentUserId(id: Int) =
        pref.setUserId(id)

    fun logout() {
        pref.removeToken()
        pref.removeRefreshToken()
        pref.removeUser()
        pref.removeUserId()
    }
}