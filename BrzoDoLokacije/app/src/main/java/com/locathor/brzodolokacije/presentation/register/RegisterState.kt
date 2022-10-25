package com.locathor.brzodolokacije.presentation.register

data class RegistrationState(
    var username: String ="",
    var name: String ="",
    var surname: String="",
    var email: String="",
    var password: String="",
    val isLoading: Boolean = false,
    val error: String? = null
)
