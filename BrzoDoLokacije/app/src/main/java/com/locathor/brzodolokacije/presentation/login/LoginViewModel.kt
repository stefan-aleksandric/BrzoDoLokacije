package com.locathor.brzodolokacije.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.locathor.brzodolokacije.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: UserRepository
): ViewModel(){
    var state by mutableStateOf(LoginState())

    fun onUsernameChange(value: String){
        state = state.copy(
            username = value
        )
    }
    fun setPasswordText(value: String) {
        state = state.copy(
            password = value
        )
    }
    init {

    }

    fun onEvent(){

    }

    private fun toDo(){

    }


}