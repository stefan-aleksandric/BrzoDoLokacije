package com.locathor.brzodolokacije

import android.Manifest
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.locathor.brzodolokacije.data.services.SessionManager
import com.locathor.brzodolokacije.presentation.NavGraphs
import com.locathor.brzodolokacije.presentation.destinations.HomeScreenDestination
import com.locathor.brzodolokacije.presentation.destinations.LoginScreenDestination

import com.locathor.brzodolokacije.presentation.ui.theme.BrzoDoLokacijeTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            //for permissions launch coroutine to consume them
        }
        permissionLauncher.launch(arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        ))
        setContent {
            BrzoDoLokacijeTheme {
                DestinationsNavHost(
                    navGraph = NavGraphs.root,
                    startRoute = if(sessionManager.getCurrentUsername() != null){
                        HomeScreenDestination
                    } else {
                        LoginScreenDestination
                    }
                )
            }
        }
    }


}