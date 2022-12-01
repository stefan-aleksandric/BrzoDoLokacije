package com.locathor.brzodolokacije.presentation.activity

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.locathor.brzodolokacije.domain.model.Post
import com.locathor.brzodolokacije.presentation.home.HomeViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

//@RootNavGraph(start = true)
@Destination
@Composable
fun ActivityScreen(
    navigator: DestinationsNavigator,
    viewModel: ActivityViewModel = hiltViewModel()
){}