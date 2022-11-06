package com.locathor.brzodolokacije.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.locathor.brzodolokacije.presentation.components.Post
import com.locathor.brzodolokacije.presentation.components.StandardScaffold
import com.locathor.brzodolokacije.presentation.posts.PostsViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: PostsViewModel = hiltViewModel()
){
        StandardScaffold (
                bottomBarOn = true,
                navigationArrowOn = true,
                topBarOn = true,
                toolbarTitle = "Home"
                ){
            Post(
                post = com.locathor.brzodolokacije.domain.model.Post(
                    ownerUsername = "Pera Peric",
                    image = "",
                    ownerProfilePicture = "",
                    desc = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout." +
                            " The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters," +
                            " as opposed to using 'Content here, content here', making it look like readable English.",
                    likeCount=10,
                    commentCount = 5,
                    title="",
                    createdAt = "",
                    longitude = 0.0,
                    latitude = 0.0
                )
            )
        }
    }

