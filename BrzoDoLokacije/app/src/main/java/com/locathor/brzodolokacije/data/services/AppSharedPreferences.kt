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

    fun removeToken() =
        prefs.edit()
            .remove(TOKEN)

    fun getRefreshToken(): String? =
        prefs.getString(REFRESH_TOKEN, null)

    fun setRefreshToken(refreshToken: String) =
        prefs.edit()
            .putString(REFRESH_TOKEN, refreshToken)
            .apply()

    fun removeRefreshToken() =
        prefs.edit()
            .remove(REFRESH_TOKEN)

    fun setUser(username: String) =
        prefs.edit()
            .putString(USER, username)
            .apply()

    fun getUser(): String? =
        prefs.getString(USER, null)

    fun removeUser() =
        prefs.edit()
            .remove(USER)


    companion object {
        const val REFRESH_TOKEN = "auth_token"
        const val TOKEN = "auth_token"
        const val USER = "auth_username"
        const val SHARED_PREFS = "APP_SHARED_PREFS"
    }
}