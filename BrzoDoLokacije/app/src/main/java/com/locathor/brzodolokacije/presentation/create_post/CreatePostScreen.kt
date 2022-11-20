package com.locathor.brzodolokacije.presentation.create_post

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.locathor.brzodolokacije.presentation.components.StandardScaffold
import com.locathor.brzodolokacije.presentation.components.StandardTextField
import com.locathor.brzodolokacije.presentation.destinations.CreatePostScreenDestination
import com.locathor.brzodolokacije.presentation.ui.theme.SpaceLarge
import com.locathor.brzodolokacije.presentation.ui.theme.SpaceMedium
import com.locathor.brzodolokacije.presentation.ui.theme.SpaceSmall
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun CreatePostScreen(
    navigator: DestinationsNavigator,
    viewModel: CreatePostViewModel = hiltViewModel()
){
    StandardScaffold (
            bottomBarOn = false,
            topBarOn = true,
            commentInputOn = false,
            toolbarTitle = "Add your post",
            searchOn = false,
            navigationArrowOn = true,
            onArrowNavigationClick = {
                navigator.popBackStack()
            }
            ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(SpaceLarge)
                .verticalScroll(rememberScrollState())
        ){
            Box(
                modifier = Modifier
                    .aspectRatio(16f / 9f)
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onBackground,
                        shape = MaterialTheme.shapes.medium
                    )
                    .clickable {
                        //TODO click on add box
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = com.locathor.brzodolokacije.R.string.add_media),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                text = viewModel.titleState.value.title,
                hint = stringResource(id = com.locathor.brzodolokacije.R.string.add_title),
                error = viewModel.titleState.value.error,
                singleLine = true,
                maxLines=1,
                onValueChange = {
                    viewModel.setTitleState(
                        CreatePostState(title = it)
                    )
                }
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                text = viewModel.locationState.value.location,
                hint = stringResource(id = com.locathor.brzodolokacije.R.string.add_location),
                error = viewModel.locationState.value.error,
                singleLine = true,
                maxLines=1,
                onValueChange = {
                    viewModel.setLocationState(
                        CreatePostState(location = it)
                    )
                }
            )
            Spacer(modifier = Modifier.height(SpaceLarge))
            StandardTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                text = viewModel.descriptionState.value.description,
                hint = stringResource(id = com.locathor.brzodolokacije.R.string.description),
                error = viewModel.descriptionState.value.error,
                singleLine = false,
                maxLines=15,
                onValueChange = {
                    viewModel.setDescriptionState(
                        CreatePostState(description = it)
                    )
                }
            )
            Spacer(modifier = Modifier.height(SpaceLarge))
            Button(
                onClick = {
                          /*TODO on post button click*/
                          },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(
                    text = stringResource(id = com.locathor.brzodolokacije.R.string.post),
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(modifier = Modifier.width(SpaceSmall))
                Icon(imageVector = Icons.Default.Send, contentDescription = null)
            }
        }
    }
}