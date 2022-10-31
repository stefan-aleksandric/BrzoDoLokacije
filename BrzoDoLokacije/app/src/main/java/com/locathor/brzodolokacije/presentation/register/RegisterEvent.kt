package com.locathor.brzodolokacije.presentation.register

sealed class RegisterEvent {
    object TogglePasswordVisibility : RegisterEvent()
    object OnRegisterButtonPress : RegisterEvent()
}

