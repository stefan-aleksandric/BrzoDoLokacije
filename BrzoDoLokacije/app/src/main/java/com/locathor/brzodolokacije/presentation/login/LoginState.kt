package com.locathor.brzodolokacije.presentation.login

import com.locathor.brzodolokacije.presentation.register.RegisterState
import com.locathor.brzodolokacije.util.AuthResult

data class LoginState(
    val username: String = "",
    val usernameError: UsernameError? = null,
    val password: String = "",
    val passwordError: PasswordError? = null,
    val isLoading: Boolean = false,
    val errorMsg: String? = null,
    val status: AuthResult<String> = AuthResult.Unauthorized()
){
    sealed class UsernameError {
        object FieldEmpty : UsernameError()
    }
    sealed class PasswordError {
        object FieldEmpty: PasswordError()
    }
}
