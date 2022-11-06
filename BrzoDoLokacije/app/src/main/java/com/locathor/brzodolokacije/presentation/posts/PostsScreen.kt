package com.locathor.brzodolokacije.presentation.posts

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination
@Composable
fun ProfileScreen(
    navigator: DestinationsNavigator,
    data: PostScreenData,
    viewModel: PostsViewModel = hiltViewModel()
) {
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.state.isLoading
    )
    val state = viewModel.state

    SwipeRefresh(state = swipeRefreshState, onRefresh = {
        viewModel.onEvent(PostsEvent.Refresh)
    }) {
//        Text(text=data.toString())
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        Text(text=state.location?.toString() ?:kotlin.run { state.error.toString() })
//        if ( state.posts.isNotEmpty() )
//            LazyColumn(modifier = Modifier.fillMaxSize()){
//                items(state.posts.size) { i ->
//                    val post = state.posts[i]
//                    Text(
//                        text = post.toString()
//                    )
//                }
//            }
//        if ( state.error != null )
//            Text(text = state.error)
    }

}