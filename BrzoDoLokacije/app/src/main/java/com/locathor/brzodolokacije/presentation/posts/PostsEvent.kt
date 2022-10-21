package com.locathor.brzodolokacije.presentation.posts

sealed class PostsEvent {
    object Refresh: PostsEvent()
    data class OnSearchQueryChange(val query: String): PostsEvent()
}