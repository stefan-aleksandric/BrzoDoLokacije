package com.locathor.brzodolokacije.presentation.posts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun ProfileScreen(
    navigator: DestinationsNavigator,
    viewModel: PostsViewModel = hiltViewModel()
) {
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.state.isLoading
    )
    val state = viewModel.state

    SwipeRefresh(state = swipeRefreshState, onRefresh = {
        viewModel.onEvent(PostsEvent.Refresh)
    }) {
        if ( state.posts.isNotEmpty() )
            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(state.posts.size) { i ->
                    val post = state.posts[i]
                    Text(
                        text = post.toString()
                    )
                }
            }
        if ( state.error != null )
            Text(text = state.error)
    }

}