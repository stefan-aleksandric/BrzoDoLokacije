package com.locathor.brzodolokacije.presentation.search

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.locathor.brzodolokacije.presentation.components.StandardScaffold
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


//@RootNavGraph(start = true)
@Destination
@Composable
fun SearchScreen(
    navigator: DestinationsNavigator,
    viewModel: SearchViewModel = hiltViewModel()
){
    StandardScaffold(
        topBarOn = true,
        bottomBarOn = false,
        navigationArrowOn = true,
        commentInputOn = false,
        searchOn = false,
        toolbarTitle = "Location search",
        onArrowNavigationClick = {
            navigator.popBackStack()
        }
    ) {

    }
}