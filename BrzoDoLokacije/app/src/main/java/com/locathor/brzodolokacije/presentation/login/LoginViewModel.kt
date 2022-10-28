package com.locathor.brzodolokacije.presentation.login

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.locathor.brzodolokacije.domain.repository.UserRepository
import com.locathor.brzodolokacije.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
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
                loginUser(state.username, state.password);
            }
        }
    }
    private fun loginUser(username: String, password: String){
        Log.d("DEBUG", "ok")
        viewModelScope.launch {
            repository.loginUser(username, password)
                .collect {  result ->
                    when(result) {
                        is Resource.Success -> {
                            result.data?.let { loginResponse ->
                                Log.d("DEBUG", loginResponse.toString())
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