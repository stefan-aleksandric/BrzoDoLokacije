package com.locathor.brzodolokacije.presentation.login

import com.locathor.brzodolokacije.presentation.register.RegisterState

data class LoginState(
    val username: String = "",
    val usernameError: UsernameError? = null,
    val password: String = "",
    val passwordError: PasswordError? = null,
    val isLoading: Boolean = false,
    val errorMsg: String? = null
){
    sealed class UsernameError {
        object FieldEmpty : UsernameError()
    }
    sealed class PasswordError {
        object FieldEmpty: PasswordError()
    }
}
