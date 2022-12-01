package com.locathor.brzodolokacije.presentation.inbox

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InboxViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    //private val repository: UserRepository
): ViewModel(){}