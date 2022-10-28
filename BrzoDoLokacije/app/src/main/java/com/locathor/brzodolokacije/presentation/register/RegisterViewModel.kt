package com.locathor.brzodolokacije.presentation.register

import android.util.Log
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
                registerUser(state.username,state.email,state.name,state.surname,state.password);
            }
        }
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