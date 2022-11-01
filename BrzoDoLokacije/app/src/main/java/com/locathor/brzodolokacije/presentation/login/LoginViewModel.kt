package com.locathor.brzodolokacije.presentation.login

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.locathor.brzodolokacije.util.AuthResult
import com.locathor.brzodolokacije.domain.repository.UserRepository
import com.locathor.brzodolokacije.presentation.register.RegisterState
import com.locathor.brzodolokacije.util.Constants
import com.locathor.brzodolokacije.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: UserRepository
): ViewModel(){
    var state by mutableStateOf(LoginState())

    fun setUsernameText(value: String){
        state = state.copy(
            username = value
        )
    }
    fun setPasswordText(value: String) {
        state = state.copy(
            password = value
        )
    }
    fun onEvent(event: LoginEvent){
        when(event){
            is LoginEvent.OnLoginButtonPress ->{
                validateUsername(state.username)
                validatePassword(state.password)

                if(state.usernameError==null && state.passwordError==null) {
                    loginUser(state.username, state.password)
                }
            }
        }
    }

    private val _showPassword = mutableStateOf(false)
    val showPassword: State<Boolean> = _showPassword

    fun setShowPassword(showPassword: Boolean) {
        _showPassword.value = showPassword
    }

    private fun validateUsername(username: String) {
        val trimmedUsername = username.trim()
        if(trimmedUsername.isBlank()) {
            state = state.copy(
                usernameError = LoginState.UsernameError.FieldEmpty
            )
            return
        }
        state = state.copy(usernameError = null)
    }

    private fun validatePassword(password: String) {
        if(password.isBlank()) {
            state = state.copy(
                passwordError = LoginState.PasswordError.FieldEmpty
            )
            return
        }
        state = state.copy(passwordError = null)
    }

    private fun loginUser(username: String, password: String){
        Log.d("DEBUG", "ok")
        viewModelScope.launch {
            repository.loginUser(username, password)
                .collect {  result ->
                    when(result) {
                        is Resource.Success -> {
                            if (result.data !is AuthResult.Authorized)
                                Log.d("AUTH_FAIL", "")
                            else
                            result.data.let { loginResponse ->
                                Log.d("AUTH_SUCCESS", loginResponse.toString())
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