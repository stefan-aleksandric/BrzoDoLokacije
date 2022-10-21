package com.locathor.brzodolokacije.presentation.profile

import com.locathor.brzodolokacije.domain.model.User

data class ProfileState(
    val users: List<User> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)