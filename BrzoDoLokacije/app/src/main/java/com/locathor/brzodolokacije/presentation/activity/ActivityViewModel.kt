package com.locathor.brzodolokacije.presentation.activity

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    //private val repository: UserRepository
): ViewModel(){}