package com.locathor.brzodolokacije.presentation.custom_map_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.locathor.brzodolokacije.domain.location.LocationTracker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomMapViewModel @Inject constructor(
    private val locationTracker: LocationTracker,
): ViewModel() {
    var state by mutableStateOf(CustomMapState())

    init {
        getLocation()
    }

    private fun getLocation() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            //get current location then if not null do let for location
            locationTracker.getLocation()?.let {
                state = state.copy(
                    selectedLocation = LatLng(it.latitude, it.longitude),
                    isLoading = false,
                    error = null
                )
                Log.d("LOCATION", state.selectedLocation.toString())
            } ?: kotlin.run {
                state = state.copy(
                    isLoading = false,
                    error = "Couldn't retreive location. Please enable location, and grant permissions!"
                )
            }
        }
    }

    fun onEvent(event: MapEvent){
        when(event){
            is MapEvent.OnMapLongClick -> {
                state = state.copy(
                    selectedLocation = event.latLng
                )
            }
            is MapEvent.FocusCurrentLocation -> {
                getLocation()
            }
            is MapEvent.OnInfoWindowLongClick -> {
                state = state.copy(
                    selectedLocation = null,
                )
            }
        }
    }
}