package com.locathor.brzodolokacije.presentation.home;

import androidx.compose.runtime.getValue
import androidx.compose.runtime.internal.isLiveLiteralsEnabled
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.locathor.brzodolokacije.data.services.SessionManager
import com.locathor.brzodolokacije.domain.location.LocationTracker
import com.locathor.brzodolokacije.domain.model.Post
import com.locathor.brzodolokacije.domain.repository.PostRepository
import com.locathor.brzodolokacije.domain.repository.UserRepository
import com.locathor.brzodolokacije.presentation.components.Post
import com.locathor.brzodolokacije.presentation.login.LoginState
import com.locathor.brzodolokacije.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val locationTracker: LocationTracker,
    private val savedStateHandle: SavedStateHandle,
    private val postRepository: PostRepository,
    private val userRepository: UserRepository,
    private val sessionManager: SessionManager
): ViewModel() {
    var state by mutableStateOf(HomeState())

    init {
        getAllPosts(true)
    }

    private fun getAllPosts(remote: Boolean = false){
        viewModelScope.launch {
            postRepository.getPosts(remote).collect{ result ->
                when(result){
                    is Resource.Success -> {
                        state = state.copy(
                            posts = result.data!!
                        )
                    }
                    is Resource.Loading -> {
                        state = state.copy(
                            isLoading = result.isLoading
                        )
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            error = result.message
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: HomeEvent){
        when(event){
            is HomeEvent.OnSwipeRefresh -> {
                getAllPosts(true)
            }
        }
    }

    fun logout(){
        sessionManager.logout();
    }
}
