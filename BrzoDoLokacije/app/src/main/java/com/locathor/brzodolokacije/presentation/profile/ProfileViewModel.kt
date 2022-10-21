package com.locathor.brzodolokacije.presentation.profile

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.locathor.brzodolokacije.domain.repository.BrzoDoLokacijeRepository
import com.locathor.brzodolokacije.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: BrzoDoLokacijeRepository
): ViewModel() {
    var state by mutableStateOf(ProfileState())

    init {
        getUsers()
    }

    fun onEvent(event: UsersEvent){
        when(event){
            is UsersEvent.Refresh -> {
                getUsers()
            }
            is UsersEvent.OnSearchQueryChange -> {
                Unit
            }
        }
    }

    private fun getUsers(){
        viewModelScope.launch {
            repository.getUsers(true).collect { result ->
                when(result) {
                    is Resource.Success -> {
                        result.data?.let { users ->
                            state = state.copy(
                                users = users
                            )
                            Log.d("STATE", users.toString())
                        }
                    }
                    is Resource.Error -> Unit
                    is Resource.Loading -> {
                        state = state.copy(isLoading = result.isLoading)
                    }
                }
            }
        }
    }
}