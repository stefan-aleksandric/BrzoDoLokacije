package com.locathor.brzodolokacije.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.locathor.brzodolokacije.domain.model.Post
import com.locathor.brzodolokacije.presentation.activity.ActivityScreen
import com.locathor.brzodolokacije.presentation.components.Post
import com.locathor.brzodolokacije.presentation.components.StandardScaffold
import com.locathor.brzodolokacije.presentation.destinations.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
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
                    navigator.navigate(UserProfileScreenDestination)
                },
                onAddButtonClick = {
                    navigator.navigate(CreatePostScreenDestination)
                },
                onSearchClick = {
                    navigator.navigate(SearchScreenDestination)
                },
                ){
            LazyColumn(
            )
            {
                items(state.list.size) { i ->
                    val post =state.list[i]
                    Post(post = post, onPostClick = {
                        navigator.navigate(PostDetailScreenDestination(post))
                    })
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
                                navigator.navigate(LoginScreenDestination)
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

