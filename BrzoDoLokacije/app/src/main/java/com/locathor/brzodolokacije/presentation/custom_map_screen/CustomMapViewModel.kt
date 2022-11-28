package com.locathor.brzodolokacije.presentation.custom_map_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.MapStyleOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
): ViewModel() {
    var state by mutableStateOf(CustomMapState())

    init {
    }


    fun onEvent(event: MapEvent){
        when(event){
            is MapEvent.ToggleFalloutMap -> {
                state = state.copy(
                    properties = state.properties.copy(
                        mapStyleOptions = if(state.isFalloutMap) {
                            null
                        } else {
                            MapStyleOptions(CustomMapStyle.json)
                        }
                    ),
                    isFalloutMap = !state.isFalloutMap
                )
            }
        }
    }
}