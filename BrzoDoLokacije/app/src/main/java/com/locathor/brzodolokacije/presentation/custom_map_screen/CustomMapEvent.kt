package com.locathor.brzodolokacije.presentation.custom_map_screen

import com.google.android.gms.maps.model.LatLng

sealed class MapEvent{
    object  ToggleFalloutMap: MapEvent()
}
