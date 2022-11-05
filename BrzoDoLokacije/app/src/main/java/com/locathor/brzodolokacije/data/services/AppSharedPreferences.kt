package com.locathor.brzodolokacije.data.services

import android.content.SharedPreferences
import javax.inject.Inject

class AppSharedPreferences @Inject constructor(
    private val prefs: SharedPreferences
) {
    fun getToken(): String? =
        prefs.getString(TOKEN, null)


    fun setToken(token: String) =
        prefs.edit()
            .putString(TOKEN, token)
            .apply()

    fun getRefreshToken(): String? =
        prefs.getString(REFRESH_TOKEN, null)

    fun setRefreshToken(refreshToken: String) =
        prefs.edit().putString(REFRESH_TOKEN, refreshToken).apply()


    companion object {
        const val REFRESH_TOKEN = "auth_token"
        const val TOKEN = "auth_token"
        const val SHARED_PREFS = "APP_SHARED_PREFS"
    }
}