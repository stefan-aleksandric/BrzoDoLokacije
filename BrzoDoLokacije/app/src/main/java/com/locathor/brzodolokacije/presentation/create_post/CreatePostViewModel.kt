package com.locathor.brzodolokacije.presentation.create_post

import androidx.compose.runtime.State
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.locathor.brzodolokacije.domain.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.Task
import com.locathor.brzodolokacije.domain.location.LocationTracker
import com.locathor.brzodolokacije.domain.model.CreatePost
import com.locathor.brzodolokacije.domain.model.Post
import com.locathor.brzodolokacije.util.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatePostViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val locationTracker: LocationTracker,
    private val repository: PostRepository,
    //private val user: User
):ViewModel() {

    var state by mutableStateOf(CreatePostState())

    private fun getLocation(): Job {
         return viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            //get current location then if not null do let for location
            locationTracker.getLocation()?.let {
                state = state.copy(
                    location = it,
                    isLoading = false,
                    error = null
                )
                Log.d("LOCATION", it.latitude.toString()+"_"+ it.longitude.toString())
            } ?: kotlin.run {
                state = state.copy(
                    isLoading = false,
                    error = "Couldn't retreive location. Please enable location, and grant permissions!"
                )
            }
        }
    }

    fun onEvent(event: CreatePostEvent){
        when(event){
            /*is PostsEvent.Refresh -> {
                getPosts()
            }
            is PostsEvent.OnSearchQueryChange -> {
                Unit
            }*/
            is CreatePostEvent.OnPostButtonPress->{
                getLocation().invokeOnCompletion {
                    createPost()
                }
            }
            else -> {}
        }
    }

    private fun createPost(){
        viewModelScope.launch {
            repository.createPost(post = CreatePost(
                    title=state.title,
                    desc=state.description,
                    mediaUris = state.mediaUris,
                    latitude = state.location!!.latitude,
                    longitude = state.location!!.longitude
                )
            ).collect{ result ->
                when(result){
                    is Resource.Error -> {
                        Log.d("ERROR", result.message.toString())
                    }
                    is Resource.Success -> {
                        Log.d("SUCCESS", "")
                    }
                    else -> {}
                }

            }


        }
    }

    fun pickMedia(mediaUris: List<Uri>){
        viewModelScope.launch {
            state = state.copy(mediaUris = mediaUris)
            Log.d("PICKED_MEDIA", mediaUris.first().path.toString())
        }
    }
}