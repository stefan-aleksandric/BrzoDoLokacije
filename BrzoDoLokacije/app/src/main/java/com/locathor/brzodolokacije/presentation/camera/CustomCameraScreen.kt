package com.locathor.brzodolokacije.presentation.camera

import android.Manifest
import android.os.Build
import android.widget.Toast
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.locathor.brzodolokacije.presentation.camera.CustomCameraViewModel
import com.locathor.brzodolokacije.R
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@OptIn(ExperimentalPermissionsApi::class)
@RootNavGraph(start = true)
@Destination
@Composable
fun CustomCameraScreen(
    viewModel: CustomCameraViewModel = hiltViewModel()
) {

    val permissions = if (Build.VERSION.SDK_INT <= 28){
        listOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    } else listOf(Manifest.permission.CAMERA)

    val permissionsState = rememberMultiplePermissionsState(
        permissions = permissions
    )

    if(!permissionsState.allPermissionsGranted){
        SideEffect {
            permissionsState.launchMultiplePermissionRequest()
        }
    }

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWith = configuration.screenWidthDp.dp
    var previewView: PreviewView

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if(permissionsState.allPermissionsGranted){
            Box(
                modifier = Modifier
                    .height(screenHeight * .85f)
                    .width(screenWith)
            ){
                AndroidView(
                    factory = {
                        previewView = PreviewView(it)
                        viewModel.showCameraPreview(previewView, lifecycleOwner)
                        previewView
                    },
                    modifier = Modifier //maybe fillmaxsize
                        .height(screenHeight * .85f)
                        .width(screenWith)
                )
            }
            Box(
                modifier = Modifier
                    .height(screenHeight * .15f),
                contentAlignment = Alignment.Center
            ){
                IconButton(onClick = {
                    if(permissionsState.allPermissionsGranted)
                        viewModel.captureAndSave(context)
                    else
                        Toast.makeText(
                            context,
                            "Please provide permissions in app settings!",
                            Toast.LENGTH_LONG
                        ).show()
                }){
                    Icon(
                        painter = painterResource(
                            id = R.drawable.ic_baseline_camera_24
                        ),
                        contentDescription = null,
                        modifier = Modifier.size(45.dp),
                        tint = Color.Magenta
                    )
                }
            }
        }
    }
}