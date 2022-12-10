package com.locathor.brzodolokacije.presentation.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.locathor.brzodolokacije.presentation.components.Post
import com.locathor.brzodolokacije.presentation.components.StandardScaffold
import com.locathor.brzodolokacije.presentation.destinations.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


//@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel = hiltViewModel()
){
    val state=viewModel.state
    val openDialog = remember { mutableStateOf(false)  }
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.state.isLoading
    )
    StandardScaffold (
        toolbarTitle = stringResource(com.locathor.brzodolokacije.R.string.home),
        topBarOn = true,
        bottomBarOn = true,
        navigationArrowOn = false,
        onLogoutButtonClick = {
            openDialog.value = true
        },
        onInboxClick = {
            navigator.navigate(InboxScreenDestination)
        },
        onActivityClick = {
            navigator.navigate(ActivityScreenDestination)
        },
        onUserProfileClick = {
            navigator.navigate(UserProfileScreenDestination(id=1))
        },
        onAddButtonClick = {
            navigator.navigate(CreatePostScreenDestination)
        },
        onSearchClick = {
            navigator.navigate(SearchScreenDestination)
        },
    ) {
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = { viewModel.onEvent(HomeEvent.OnSwipeRefresh) }
        ) {
            LazyColumn {
                items(state.posts) { post ->
                    Post(post = post,
                        onUsernameClick = { navigator.navigate(UserProfileScreenDestination(id=1)) },
                        onPostClick = { navigator.navigate(PostDetailScreenDestination(post)) }
                    )
                }
            }
            if (openDialog.value) {
                AlertDialog(
                    onDismissRequest = {
                        openDialog.value = false
                    },
                    title = {
                        Text(text = "Logging out")
                    },
                    text = {
                        Text(text = "Are you sure you want to logout?")
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                openDialog.value = false
                                viewModel.logout()
                                navigator.popBackStack()
                                navigator.navigate(
                                    LoginScreenDestination,
                                )
                            }) {
                            Text(text = "Confirm")
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                openDialog.value = false
                            }) {
                            Text(text = "Cancel")
                        }
                    }
                )
            }
        }
    }
}

