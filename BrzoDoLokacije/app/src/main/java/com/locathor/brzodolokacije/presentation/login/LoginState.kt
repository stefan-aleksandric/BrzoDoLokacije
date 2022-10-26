package com.locathor.brzodolokacije.presentation.login

data class LoginState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMsg: String? = null
)
