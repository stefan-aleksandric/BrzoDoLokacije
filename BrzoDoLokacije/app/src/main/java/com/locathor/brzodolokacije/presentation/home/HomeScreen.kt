package com.locathor.brzodolokacije.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
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
import com.locathor.brzodolokacije.presentation.posts.PostsViewModel
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
        StandardScaffold (
                toolbarTitle = stringResource(com.locathor.brzodolokacije.R.string.home),
                topBarOn = true,
                bottomBarOn = true,
                navigationArrowOn = false,
                onLogoutButtonClick = {
                    navigator.navigate(LoginScreenDestination)
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
        }
}

