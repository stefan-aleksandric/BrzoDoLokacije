package com.locathor.brzodolokacije.presentation.create_post

import androidx.compose.runtime.State
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.locathor.brzodolokacije.domain.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatePostViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: PostRepository,
):ViewModel() {

    private val _descriptionState = mutableStateOf(CreatePostState())
    val descriptionState: State<CreatePostState> = _descriptionState
    private val _titleState = mutableStateOf(CreatePostState())
    val titleState: State<CreatePostState> = _titleState
    private val _locationState = mutableStateOf(CreatePostState())
    val locationState: State<CreatePostState> = _locationState

    fun setDescriptionState(state: CreatePostState) {
        _descriptionState.value = state
    }

    fun setTitleState(state: CreatePostState) {
        _titleState.value = state
    }

    fun setLocationState(state: CreatePostState) {
        _locationState.value = state
    }

    var state by mutableStateOf(CreatePostState())


    fun pickMedia(mediaUris: List<Uri>){
        viewModelScope.launch {
            state = state.copy(mediaUris = mediaUris)
        }
    }
}