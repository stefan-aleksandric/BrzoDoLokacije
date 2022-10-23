package com.locathor.brzodolokacije.presentation.posts

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.locathor.brzodolokacije.data.remote.UserApi
import com.locathor.brzodolokacije.data.repository.UserRepositoryImpl
import com.locathor.brzodolokacije.domain.repository.PostRepository
import com.locathor.brzodolokacije.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: PostRepository
): ViewModel() {
    var state by mutableStateOf(PostsState())

    init {
        getPosts()
    }

    fun onEvent(event: PostsEvent){
        when(event){
            is PostsEvent.Refresh -> {
                getPosts()
            }
            is PostsEvent.OnSearchQueryChange -> {
                Unit
            }
        }
    }

    private fun getPosts(){
        viewModelScope.launch {
            repository.getPosts(true).collect { result ->
                when(result) {
                    is Resource.Success -> {
                        result.data?.let { posts ->
                            state = state.copy(
                                posts = posts
                            )
                            Log.d("STATE", posts.toString())
                        }
                    }
                    is Resource.Error -> {
                        result.message?.let { message ->
                            state = state.copy(
                                error = message
                            )
                        }
                    }
                    is Resource.Loading -> {
                        state = state.copy(isLoading = result.isLoading)
                    }
                }
            }
        }
    }
}