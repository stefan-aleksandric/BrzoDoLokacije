package com.locathor.brzodolokacije.presentation.register

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
class RegisterViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: UserRepository
): ViewModel(){

    private val _state = mutableStateOf(RegisterState())
    val state: State<RegisterState> = _state

    fun onEvent(event: RegisterEvent) {
        when(event) {
            is RegisterEvent.EnteredUsername -> {
                _state.value = _state.value.copy(
                    username = event.value
                )
            }
            is RegisterEvent.EnteredEmail -> {
                _state.value = _state.value.copy(
                    email = event.value
                )
            }
            is RegisterEvent.EnteredPassword -> {
                _state.value = _state.value.copy(
                    password = event.value
                )
            }
            is RegisterEvent.TogglePasswordVisibility -> {
                _state.value = _state.value.copy(
                    isPasswordVisible = !state.value.isPasswordVisible
                )
            }
            else -> {}
        }
    }

}