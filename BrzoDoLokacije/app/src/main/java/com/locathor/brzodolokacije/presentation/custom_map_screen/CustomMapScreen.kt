package com.locathor.brzodolokacije.presentation.custom_map_screen


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ToggleOff
import androidx.compose.material.icons.filled.ToggleOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomMapScreen(
    viewModel: MapViewModel = hiltViewModel()
){
    val state = viewModel.state
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val uiSettings = remember {
        MapUiSettings(zoomControlsEnabled = false)
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            var clickCount by remember { mutableStateOf(0) }
            FloatingActionButton(onClick = {
                viewModel.onEvent(MapEvent.ToggleFalloutMap)
//                scope.launch {
//                    snackbarHostState.showSnackbar(
//                        "Snackbar # ${++clickCount}"
//                    )
//                }
            }) {
                Icon(
                    imageVector = if(state.isFalloutMap){
                        Icons.Default.ToggleOff
                    } else {
                        Icons.Default.ToggleOn
                    },
                    contentDescription = "Toggle Fallout map"
                )
            }
        },
    ){
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            properties = state.properties,
            uiSettings = uiSettings,
            onMapLongClick = {
//                viewModel.onEvent(MapEvent.OnMapLongClick(it))
            }
        ) {
//            viewModel.state.parkingSpots.forEach { spot ->
//                Marker(
//                    state = MarkerState(
//                        position = LatLng(spot.latitude, spot.longitude),
//                    ),
//                    title = "Parking spot (${spot.latitude},${spot.longitude})",
//                    snippet = "Long click to delete",
//                    onInfoWindowLongClick = {
//                        viewModel.onEvent(MapEvent.OnInfoWindowLongClick(spot))
//                    },
//                    onClick = {
//                        it.showInfoWindow()
//                        true
//                    },
//                    icon = BitmapDescriptorFactory.defaultMarker(
//                        BitmapDescriptorFactory.HUE_GREEN
//                    )
//                )
//            }
        }
    }
}