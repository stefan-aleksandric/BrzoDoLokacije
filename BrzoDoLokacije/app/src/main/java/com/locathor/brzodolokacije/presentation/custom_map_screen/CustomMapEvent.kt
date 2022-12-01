package com.locathor.brzodolokacije.presentation.custom_map_screen

import com.google.android.gms.maps.model.LatLng

sealed class MapEvent{
    object  FocusCurrentLocation: MapEvent()
    data class OnMapLongClick(val latLng: LatLng): MapEvent()
    object OnInfoWindowLongClick: MapEvent()
}
