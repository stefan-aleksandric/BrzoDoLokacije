package com.locathor.brzodolokacije.presentation.custom_map_screen


import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MyLocation
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
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.result.EmptyResultBackNavigator
import com.ramcosta.composedestinations.result.ResultBackNavigator

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
//@RootNavGraph(start = true)
@Destination
@Composable
fun CustomMapScreen(
    viewModel: CustomMapViewModel = hiltViewModel(),
    backNavigator: ResultBackNavigator<LatLng>
){
    val singapore = LatLng(1.35, 103.87)
    val state = viewModel.state
    BackHandler(enabled = true) {
        if(state.selectedLocation != null){
            backNavigator.navigateBack(result = state.selectedLocation)
        }
    }
    val uiSettings = remember {
        MapUiSettings(zoomControlsEnabled = false)
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(MapEvent.FocusCurrentLocation)
            }) {
                Icon(
                    imageVector = Icons.Default.MyLocation,
                    contentDescription = "Focus my current Location"
                )
            }
        },
    )
    {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            properties = state.properties,
            uiSettings = uiSettings,
            onMapLongClick = {
                viewModel.onEvent(MapEvent.OnMapLongClick(it))
            },
        ) {
            if(state.selectedLocation != null) {
                Marker(
                    state = MarkerState(
                        position = state.selectedLocation,
                    ),
                    title = "Your selected post location (${state.selectedLocation.toString()})",
                    snippet = "Long click to delete",
                    onInfoWindowLongClick = {
                        viewModel.onEvent(MapEvent.OnInfoWindowLongClick)
                    },
                    onClick = {
                        it.showInfoWindow()
                        false
                    },
                    icon = BitmapDescriptorFactory.defaultMarker(
                        BitmapDescriptorFactory.HUE_AZURE
                    )
                )
            }

        }
    }
}