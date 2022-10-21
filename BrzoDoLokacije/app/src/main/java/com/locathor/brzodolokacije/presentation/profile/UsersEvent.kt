package com.locathor.brzodolokacije.presentation.profile

sealed class UsersEvent {
    object Refresh: UsersEvent()
    data class OnSearchQueryChange(val query: String): UsersEvent()
}