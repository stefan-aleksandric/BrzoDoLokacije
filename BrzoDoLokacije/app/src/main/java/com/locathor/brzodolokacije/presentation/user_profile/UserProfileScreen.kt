package com.locathor.brzodolokacije.presentation.user_profile

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.locathor.brzodolokacije.presentation.components.StandardScaffold
import com.locathor.brzodolokacije.presentation.home.HomeViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun UserProfileScreen(
    navigator: DestinationsNavigator,
    viewModel: UserProfileViewModel = hiltViewModel()
){
    StandardScaffold(
        topBarOn = true,
        bottomBarOn = false,
        navigationArrowOn = true,
        commentInputOn = false,
        searchOn = false,
        toolbarTitle = "User profile",
        onArrowNavigationClick = {
            navigator.popBackStack()
        }
    ) {

    }
}