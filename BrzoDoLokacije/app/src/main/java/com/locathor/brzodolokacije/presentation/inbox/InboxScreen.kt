package com.locathor.brzodolokacije.presentation.inbox

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.locathor.brzodolokacije.presentation.components.StandardScaffold
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

//@RootNavGraph(start = true)
@Destination
@Composable
fun InboxScreen(
    navigator: DestinationsNavigator,
    viewModel: InboxViewModel = hiltViewModel()
){
    StandardScaffold(
        topBarOn = true,
        bottomBarOn = false,
        navigationArrowOn = true,
        commentInputOn = false,
        searchOn = false,
        toolbarTitle = "Inbox",
        onArrowNavigationClick = {
            navigator.popBackStack()
        }
    ) {

    }
}