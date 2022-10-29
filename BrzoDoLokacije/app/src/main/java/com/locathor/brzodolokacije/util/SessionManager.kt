package com.locathor.brzodolokacije.util

import android.content.SharedPreferences
import javax.inject.Inject


class SessionManager (
    val prefs: SharedPreferences
){
    fun getToken(): String? {
        return prefs.getString("token", null)
    }

    fun setToken(token: String) {
        prefs.edit()
            .putString("token", token)
            .apply()
    }
}