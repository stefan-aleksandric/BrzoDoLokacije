package com.locathor.brzodolokacije.presentation.custom_map_screen

import android.location.Location
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MapProperties

data class CustomMapState(
    val properties: MapProperties = MapProperties(),
    val isFalloutMap: Boolean = false,
    val selectedLocation: LatLng? = null,
    val location: LatLng? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
