package com.locathor.brzodolokacije.presentation.user_profile

import android.provider.Telephony.Mms.Inbox
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.locathor.brzodolokacije.R
import com.locathor.brzodolokacije.presentation.components.Post
import com.locathor.brzodolokacije.presentation.components.StandardScaffold
import com.locathor.brzodolokacije.presentation.destinations.InboxScreenDestination
import com.locathor.brzodolokacije.presentation.home.HomeViewModel
import com.locathor.brzodolokacije.presentation.register.RegisterEvent
import com.locathor.brzodolokacije.presentation.ui.theme.ProfilePictureSize
import com.locathor.brzodolokacije.presentation.ui.theme.SpaceMedium
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun UserProfileScreen(
    id: Int,
    viewModel: UserProfileViewModel = hiltViewModel(),
    navigator: DestinationsNavigator,
){

    val owner=true
    val state=viewModel.state

    StandardScaffold(
        topBarOn = true,
        bottomBarOn = false,
        navigationArrowOn = true,
        commentInputOn = false,
        searchOn = false,
        toolbarTitle = "User profile",
        onArrowNavigationClick = {
            navigator.popBackStack()
        }
    ) {
        LazyColumn(
            modifier=Modifier.fillMaxSize(),
        ){
            item{
                //TODO ADD MAP
                Image(
                    painter = painterResource(id = R.drawable.map),
                    contentDescription = stringResource(id = R.string.map_image),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            //TODO click on user map
                        }
                )
            }
            item{
                Box(modifier = Modifier
                    .fillMaxWidth()
                ){

                }
                Row(
                    modifier= Modifier
                        .fillMaxWidth()
                        .padding(SpaceMedium),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,

                ){
                    Image(
                        //TODO pull user map image
                        painterResource(id = R.drawable.user),
                        contentDescription="User image",
                        modifier= Modifier
                            .size(ProfilePictureSize)
                            .clip(CircleShape)
                            .clickable {
                                //TODO click to change user picture
                            }
                    )
                    Text(
                        //TODO pull user name
                        text="Pera Peric",
                        style= MaterialTheme.typography.displaySmall
                    )
                    if(owner){
                        IconButton(onClick = {
                        /*TODO on EDIT ICON CLICK*/
                        }) {
                            Icon(
                                Icons.Filled.Edit,
                                contentDescription = "Edit",
                            )
                        }
                    }
                }
                Row(
                    modifier= Modifier
                        .fillMaxWidth()
                        .padding(SpaceMedium),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(
                            text="Posts",
                        )
//                        Text(
//                            text=state.posts,
//                            fontWeight = FontWeight.Bold,
//                            textAlign = TextAlign.Center
//                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(
                            text="Likes"
                        )
                        Text(
                            text="10",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                    if(owner){
                        Button(
                            onClick = {
                                navigator.navigate(InboxScreenDestination)
                            }
                        ) {
                            Text(
                                text = "Inbox",
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }else{
                        Button(
                            onClick = {
                                //TODO navigation to user chat
                            }
                        ) {
                            Text(
                                text = stringResource(id = R.string.message_me),
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
            //TODO GET POSTS FROM USER ONLY
            items(state.posts.size) { i ->
                val post =state.posts[i]
                Post(post = post, onPostClick = {
                    navigator.navigate(com.locathor.brzodolokacije.presentation.destinations.PostDetailScreenDestination(post))
                })
            }
        }
    }
}