package com.locathor.brzodolokacije.presentation.create_post

import androidx.compose.runtime.Composable
import com.locathor.brzodolokacije.presentation.components.StandardScaffold
import com.locathor.brzodolokacije.presentation.destinations.CreatePostScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun CreatePostScreen(
    navigator: DestinationsNavigator,
){
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

    }
}