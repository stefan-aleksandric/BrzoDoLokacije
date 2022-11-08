package com.locathor.brzodolokacije.presentation.post_detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.locathor.brzodolokacije.R
import com.locathor.brzodolokacije.domain.model.Comment
import com.locathor.brzodolokacije.domain.model.Post
import com.locathor.brzodolokacije.presentation.components.ActionRow
import com.locathor.brzodolokacije.presentation.components.Post
import com.locathor.brzodolokacije.presentation.components.StandardScaffold
import com.locathor.brzodolokacije.presentation.posts.PostsViewModel
import com.locathor.brzodolokacije.presentation.ui.theme.ProfilePictureSize
import com.locathor.brzodolokacije.presentation.ui.theme.SpaceMedium
import com.locathor.brzodolokacije.presentation.ui.theme.SpaceSmall
import com.locathor.brzodolokacije.util.Constants
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

//@RootNavGraph(start = true)
//@Destination
@Composable
fun PostDetailScreen(
    post: Post
){
    StandardScaffold (
        bottomBarOn = false,
        navigationArrowOn = true,
        topBarOn = true,
        searchOn= false,
        toolbarTitle = post.title
    ){
        Box(
            modifier= Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = ProfilePictureSize / 2f)
                    .clip(MaterialTheme.shapes.small)
                    .shadow(4.dp)
                    .padding(SpaceSmall)
                    .fillMaxSize()
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            //TODO on image click
                        }
                ){
                    Image(
                        //TODO pull user post image async
                        painterResource(id = R.drawable.paris),
                        contentDescription="Post image",
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
                Row (
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .clickable {
                            //TODO on location click
                        }
                ){
                    Icon(
                        Icons.Filled.LocationOn,
                        contentDescription = "Location pin icon" // decorative element
                    )
                    //TODO addLocation from user
                    Text(
                        text="Paris,France,Western Europe",
                        style=MaterialTheme.typography.titleSmall
                    )
                }
                ActionRow(
                    userIconOn=false,
                    username=post.ownerUsername,
                    modifier=Modifier.fillMaxWidth(),
                    onLikeClick = { isLiked->

                    },
                    onCommentClick = {

                    },
                    onShareClick = {

                    },
                    onUsernameClick = { username->

                    }
                )
                Spacer(modifier = Modifier.height(SpaceSmall))
                Text(
                    text=post.desc,
                    style = MaterialTheme.typography.bodySmall,
                )
                Spacer(modifier = Modifier.height(SpaceSmall))
                Text(
                    text= stringResource(id = R.string.liked_by_x_people,post.likeCount),
                    style=MaterialTheme.typography.titleSmall
                )
            }
            Image(
                //TODO pull user post image async
                painterResource(id = R.drawable.user),
                contentDescription="User image",
                modifier= Modifier
                    .size(ProfilePictureSize)
                    .clip(CircleShape)
                    .align(Alignment.TopCenter)
                    .clickable {
                        //TODO navigation to user profile
                    }
            )
        }
    }
}

@RootNavGraph(start = true)
@Destination
@Preview
@Composable
fun PostDetailPreview(){
    PostDetailScreen(
        post= Post(
                ownerUsername = "Pera Peric",
                image = "",
                ownerProfilePicture = "",
                desc = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout." +
                        " The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters," +
                        " as opposed to using 'Content here, content here', making it look like readable English.",
                likeCount=10,
                commentCount = 5,
                title="Paris travel experience",
                createdAt = "",
                longitude = 0.0,
                latitude = 0.0
            )
        )
}

@Composable
fun Comment(
    modifier:Modifier=Modifier,
    comment: Comment =Comment(),
    onLikeClick:(Boolean)->Unit={}
){
    Card(
        modifier= modifier
            .padding(SpaceMedium)
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.onSurface),
    ){
        Column(modifier=Modifier.fillMaxSize()){
            Row(
                modifier=Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Row(
                    modifier=Modifier
                ){
                    Image(painter= painterResource(
                        id = R.drawable.user)
                        ,null
                    )
                    Spacer(modifier=Modifier.width(SpaceSmall))
                    Text(
                        text=comment.username,
                        fontWeight = FontWeight.Bold
                    )
                }
                //TODO timestamp instead of text
                Text(
                    text="2 days ago"
                )
            }
            Spacer(modifier=Modifier.height(SpaceMedium))
            Row(
                modifier=Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text=comment.comment,
                    style=MaterialTheme.typography.bodySmall
                )
                IconButton(
                    onClick = {
                    onLikeClick(comment.isLiked)
                }){
                    Icon(imageVector=Icons.Default.Favorite,
                    if(comment.isLiked){
                        stringResource(id=R.string.like)
                    }else stringResource(id = R.string.unlike)
                    )
                }
            }
            Spacer(modifier=Modifier.height(SpaceMedium))
            Text(
                text= stringResource(id = R.string.liked_by_x_people,comment.likeCount),
                fontWeight = FontWeight.Bold,
                style=MaterialTheme.typography.bodySmall
            )
        }
    }
}

