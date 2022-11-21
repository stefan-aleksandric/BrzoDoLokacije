package com.locathor.brzodolokacije.presentation.create_post

sealed class CreatePostEvent {
    object OnGetLocationButtonPress : CreatePostEvent()
    object OnPostButtonPress: CreatePostEvent()
    object OnAddMediaButtonPress: CreatePostEvent()
    object OnCameraButtonPress: CreatePostEvent()
}