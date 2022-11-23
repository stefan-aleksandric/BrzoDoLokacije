package com.locathor.brzodolokacije.presentation.register

import com.locathor.brzodolokacije.util.AuthResult

data class RegisterState(
    var username: String ="",
    val usernameError: UsernameError? = null,
    var name: String ="",
    val nameError: NameError? = null,
    var surname: String="",
    val surnameError: SurnameError? = null,
    var email: String="",
    val emailError: EmailError? = null,
    var password: String="",
    val passwordError: PasswordError? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val status: AuthResult<String> = AuthResult.Unauthorized(),
    val isPasswordVisible: Boolean = false
){
    sealed class UsernameError {
        object FieldEmpty : UsernameError()
        object InputTooShort : UsernameError()
    }

    sealed class NameError {
        object FieldEmpty : NameError()
        object InputTooShort : NameError()
    }

    sealed class SurnameError {
        object FieldEmpty : SurnameError()
        object InputTooShort : SurnameError()
    }

    sealed class EmailError {
        object FieldEmpty : EmailError()
        object InvalidEmail: EmailError()
    }

    sealed class PasswordError {
        object FieldEmpty: PasswordError()
        object InvalidPassword : PasswordError()
        object InputTooShort : PasswordError()
    }
}
