package com.locathor.brzodolokacije.presentation.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.locathor.brzodolokacije.domain.location.LocationTracker
import com.locathor.brzodolokacije.domain.model.Post
import com.locathor.brzodolokacije.domain.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class HomeState(
    val posts: List<Post> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)