package com.locathor.brzodolokacije.presentation.register

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.locathor.brzodolokacije.domain.repository.UserRepository
import com.locathor.brzodolokacije.presentation.login.LoginEvent
import com.locathor.brzodolokacije.presentation.login.LoginState
import com.locathor.brzodolokacije.util.Constants
import com.locathor.brzodolokacije.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: UserRepository
): ViewModel(){

    var state by mutableStateOf(RegisterState())

    fun setEmailText(value: String){
        state = state.copy(
            email = value
        )
    }
    fun setUsernameText(value: String){
        state = state.copy(
            username = value
        )
    }
    fun setNameText(value: String){
        state = state.copy(
            name = value
        )
    }
    fun setSurnameText(value: String){
        state = state.copy(
            surname = value
        )
    }
    fun setPasswordText(value: String){
        state = state.copy(
            password = value
        )
    }

    fun onEvent(event: RegisterEvent){
        when(event){
            is RegisterEvent.OnRegisterButtonPress ->{
                validateUsername(state.username)
                validateEmail(state.email)
                validatePassword(state.password)
                validateName(state.name)
                validateSurname(state.surname)

                if(state.usernameError==null && state.emailError==null && state.nameError==null && state.surnameError==null && state.passwordError==null){
                    registerUser(state.username,state.email,state.name,state.surname,state.password)
                }
            }
            is RegisterEvent.TogglePasswordVisibility -> {
                state = state.copy(
                    isPasswordVisible = !state.isPasswordVisible
                )
            }
        }
    }

    private fun validateUsername(username: String) {
        val trimmedUsername = username.trim()
        if(trimmedUsername.isBlank()) {
            state = state.copy(
                usernameError = RegisterState.UsernameError.FieldEmpty
            )
            return
        }
        if(trimmedUsername.length < Constants.MIN_USERNAME_LENGTH) {
            state = state.copy(
                usernameError = RegisterState.UsernameError.InputTooShort
            )
            return
        }
        state = state.copy(usernameError = null)
    }

    private fun validateEmail(email: String) {
        val trimmedEmail = email.trim()
        if(trimmedEmail.isBlank()) {
            state = state.copy(
                emailError = RegisterState.EmailError.FieldEmpty
            )
            return
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            state = state.copy(
                emailError = RegisterState.EmailError.InvalidEmail
            )
            return
        }
        state = state.copy(emailError = null)
    }

    private fun validateName(name: String) {
        val trimmedName = name.trim()
        if(trimmedName.isBlank()) {
            state = state.copy(
                nameError = RegisterState.NameError.FieldEmpty
            )
            return
        }
        if(trimmedName.length < Constants.MIN_NAME_SURNAME_LENGTH) {
            state = state.copy(
                nameError = RegisterState.NameError.InputTooShort
            )
            return
        }
        state = state.copy(nameError = null)
    }

    private fun validateSurname(surname: String) {
        val trimmedSurname = surname.trim()
        if(trimmedSurname.isBlank()) {
            state = state.copy(
                surnameError = RegisterState.SurnameError.FieldEmpty
            )
            return
        }
        if(trimmedSurname.length < Constants.MIN_NAME_SURNAME_LENGTH) {
            state = state.copy(
                surnameError = RegisterState.SurnameError.InputTooShort
            )
            return
        }
        state = state.copy(surnameError = null)
    }

    private fun validatePassword(password: String) {
        if(password.isBlank()) {
            state = state.copy(
                passwordError = RegisterState.PasswordError.FieldEmpty
            )
            return
        }
        if(password.length < Constants.MIN_PASSWORD_LENGTH) {
            state= state.copy(
                passwordError = RegisterState.PasswordError.InputTooShort
            )
            return
        }
        val capitalLettersInPassword = password.any { it.isUpperCase() }
        val numberInPassword = password.any { it.isDigit() }
        if(!capitalLettersInPassword || !numberInPassword) {
            state = state.copy(
                passwordError = RegisterState.PasswordError.InvalidPassword
            )
            return
        }
        state = state.copy(passwordError = null)
    }

    private fun registerUser(username: String, email: String,name: String,surname: String,password: String){
        Log.d("DEBUG", "ok")
        viewModelScope.launch {
            repository.registerUser(username,email,name,surname,password)
                .collect {  result ->
                    when(result) {
                        is Resource.Success -> {
                            result.data?.let { UserDto ->
                                Log.d("DEBUG", UserDto.toString())
                            }
                        }
                        is Resource.Error -> {
                            Unit
                        }
                        is Resource.Loading -> {
                            Unit
                        }
                    }
                }
        }

    }

}