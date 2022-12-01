package com.locathor.brzodolokacije.presentation.create_post

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import android.Manifest
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.activity.result.contract.ActivityResultContracts.PickMultipleVisualMedia
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.text.font.FontWeight
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.android.gms.maps.model.LatLng
import com.locathor.brzodolokacije.presentation.camera.CustomCameraScreen
import com.locathor.brzodolokacije.presentation.components.StandardScaffold
import com.locathor.brzodolokacije.presentation.components.StandardTextField
import com.locathor.brzodolokacije.presentation.destinations.CustomCameraScreenDestination
import com.locathor.brzodolokacije.presentation.destinations.CustomMapScreenDestination
import com.locathor.brzodolokacije.presentation.posts.PostsEvent
import com.locathor.brzodolokacije.presentation.ui.theme.SpaceLarge
import com.locathor.brzodolokacije.presentation.ui.theme.SpaceMedium
import com.locathor.brzodolokacije.presentation.ui.theme.SpaceSmall
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.NavResult
import com.ramcosta.composedestinations.result.ResultRecipient

@OptIn(ExperimentalPermissionsApi::class)
//@RootNavGraph(start = true)
@Destination
@Composable
fun CreatePostScreen(
    navigator: DestinationsNavigator,
    viewModel: CreatePostViewModel = hiltViewModel(),
    resultRecipient: ResultRecipient<CustomMapScreenDestination, LatLng>
){
    val state = viewModel.state

    resultRecipient.onNavResult { result ->
        when (result) {
            is NavResult.Canceled -> {
                // `Destination` was shown but it was canceled
                // and no value was set (example: dialog/bottom sheet dismissed)
            }
            is NavResult.Value -> {
                viewModel.setLocation(result.value)
                // Do whatever with the result received!
                // Think of it like a button click, usually you want to call
                // a view model method here or navigate somewhere
            }
        }
    }
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = state.isLoading
    )

    val permission = Manifest.permission.READ_EXTERNAL_STORAGE
    val permissionState = rememberPermissionState(
        permission = permission
    )
    if(!permissionState.status.isGranted){
        SideEffect {
            permissionState.launchPermissionRequest()
        }
    }


    val launcher = rememberLauncherForActivityResult(contract = PickMultipleVisualMedia()) { uris ->
//        val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
//        uris.forEach {
//            context.contentResolver.takePersistableUriPermission(it, flag)
//        }

        if (uris.isNotEmpty()) {
            Log.d("PhotoPicker", "Number of items selected: ${uris.size}")
            viewModel.pickMedia(uris)
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

    StandardScaffold (
        bottomBarOn = false,
        topBarOn = true,
        commentInputOn = false,
        toolbarTitle = "Add your post",
        searchOn = false,
        navigationArrowOn = true,
        onArrowNavigationClick = {
            navigator.popBackStack()
        }
    ){
        SwipeRefresh(state = swipeRefreshState, onRefresh = {})
        {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(SpaceLarge)
                    .verticalScroll(rememberScrollState())
            ){
                Box(
                    modifier = Modifier
                        //.aspectRatio(16f / 9f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier=Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ){
                        IconButton(
                            onClick = {
                                launcher.launch(PickVisualMediaRequest(PickVisualMedia.ImageAndVideo))
                            },
                            modifier=Modifier.border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.onBackground,
                                shape = MaterialTheme.shapes.large
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = stringResource(id = com.locathor.brzodolokacije.R.string.add_media),
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        }
                        IconButton(
                            onClick = {
                                      navigator.navigate(CustomMapScreenDestination())
                            },
                            modifier=Modifier.border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.onBackground,
                                shape = MaterialTheme.shapes.large
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Map,
                                contentDescription = stringResource(id = com.locathor.brzodolokacije.R.string.pick_location),
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        }
                        IconButton(
                            onClick = {
                                      navigator.navigate(CustomCameraScreenDestination())
                            },
                            modifier=Modifier.border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.onBackground,
                                shape = MaterialTheme.shapes.large
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Camera,
                                contentDescription = stringResource(id = com.locathor.brzodolokacije.R.string.take_picture),
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(SpaceMedium))
                Text(
                    text = if(!state.mediaUris.isNullOrEmpty()) {
                        stringResource(com.locathor.brzodolokacije.R.string.media_selected,state.mediaUris.size)
                    }else{
                        stringResource(id = com.locathor.brzodolokacije.R.string.no_media_selected)
                    },
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(SpaceMedium))
                StandardTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = state.title,
                    hint = stringResource(id = com.locathor.brzodolokacije.R.string.add_title),
                    error = "",
                    singleLine = true,
                    maxLines=1,
                    onValueChange = {
                        viewModel.setTitleText(it)
                    }
                )
                Spacer(modifier = Modifier.height(SpaceMedium))
                Text(
                    text = if(state.error!=null) {
                        stringResource(id = com.locathor.brzodolokacije.R.string.location_unreachable)
                    }else{
                        "Location: longitude:"+state.location?.longitude.toString()+",latitude:"+state.location?.latitude.toString()
                    },
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(SpaceLarge))
                StandardTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = state.description,
                    hint = stringResource(id = com.locathor.brzodolokacije.R.string.description),
                    error = "",
                    singleLine = false,
                    maxLines=15,
                    onValueChange = {
                        viewModel.setDescriptionText(it)
                    }
                )
                Spacer(modifier = Modifier.height(SpaceLarge))
                Button(
                    onClick = {
                        viewModel.onEvent(CreatePostEvent.OnPostButtonPress)
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(
                        text = stringResource(id = com.locathor.brzodolokacije.R.string.post),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Spacer(modifier = Modifier.width(SpaceSmall))
                    Icon(imageVector = Icons.Default.Send, contentDescription = null)
                }
            }
        }
    }
}