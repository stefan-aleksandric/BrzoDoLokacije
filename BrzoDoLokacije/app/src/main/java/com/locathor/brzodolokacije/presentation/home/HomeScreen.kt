package com.locathor.brzodolokacije.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.locathor.brzodolokacije.domain.model.Post
import com.locathor.brzodolokacije.presentation.components.Post
import com.locathor.brzodolokacije.presentation.components.StandardScaffold
import com.locathor.brzodolokacije.presentation.destinations.PostDetailScreenDestination
import com.locathor.brzodolokacije.presentation.posts.PostsViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel = hiltViewModel()
){
        var state=viewModel.state
        StandardScaffold (
                bottomBarOn = true,
                navigationArrowOn = false,
                topBarOn = true,
                toolbarTitle = stringResource(com.locathor.brzodolokacije.R.string.home)
                ){
            LazyColumn(
            ) {
                items(state.list.size) { i ->
                    val post =state.list[i]
                    Post(post = post, onPostImageClick = {
                        navigator.navigate(PostDetailScreenDestination(post))
                    })
                }
            }
        }
}

