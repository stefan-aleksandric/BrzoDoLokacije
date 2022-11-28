package com.locathor.brzodolokacije.presentation.custom_map_screen

import com.google.maps.android.compose.MapProperties

data class CustomMapState(
    val properties: MapProperties = MapProperties(),
    val isFalloutMap: Boolean = false,
)
