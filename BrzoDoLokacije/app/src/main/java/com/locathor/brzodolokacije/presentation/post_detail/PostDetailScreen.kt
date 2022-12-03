package com.locathor.brzodolokacije.presentation.post_detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Input
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.locathor.brzodolokacije.R
import com.locathor.brzodolokacije.domain.model.Comment
import com.locathor.brzodolokacije.domain.model.Post
import com.locathor.brzodolokacije.presentation.components.ActionRow
import com.locathor.brzodolokacije.presentation.components.StandardScaffold
import com.locathor.brzodolokacije.presentation.components.StandardTextField
import com.locathor.brzodolokacije.presentation.destinations.UserProfileScreenDestination
import com.locathor.brzodolokacije.presentation.ui.theme.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

//@RootNavGraph(start = true)
@Destination
@Composable
fun PostDetailScreen(
    post: Post,
    navigator:DestinationsNavigator,
    commentInput:String?=""
){
    StandardScaffold (
        bottomBarOn = false,
        commentInputOn = true,
        navigationArrowOn = true,
        topBarOn = true,
        searchOn= false,
        toolbarTitle = post.title,
        onArrowNavigationClick = {
            navigator.popBackStack()
        }
    ){
        Box(
            modifier= Modifier
                .fillMaxSize()
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = ProfilePictureSize / 2f)
                    .clip(MaterialTheme.shapes.small)
                    .fillMaxSize()
            ){
                LazyColumn(
                    modifier=Modifier
                        .fillMaxSize()
                ){
                    item{
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
                                .padding(horizontal = SpaceSmall)
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
                            modifier= Modifier
                                .fillMaxWidth()
                                .padding(horizontal = SpaceSmall),
                            onLikeClick = { isLiked->

                            },
                            onCommentClick = {

                            },
                            onShareClick = {

                            },
                            onUsernameClick = {
                                //TODO ON USERNAME UNDER POST CLICK
                                navigator.navigate(UserProfileScreenDestination)
                            }
                        )
                        Spacer(modifier = Modifier.height(SpaceSmall))
                        Text(
                            modifier = Modifier
                                .padding(horizontal= SpaceSmall),
                            text=post.desc,
                            style = MaterialTheme.typography.bodySmall,
                        )
                        Spacer(modifier = Modifier.height(SpaceSmall))
                        Text(
                            modifier = Modifier
                                .padding(horizontal=SpaceSmall),
                            text= stringResource(id = R.string.liked_by_x_people,post.likeCount),
                            style=MaterialTheme.typography.titleSmall
                        )
                        Spacer(modifier = Modifier.height(SpaceSmall))
                    }
                    items(20){
                        Comment(
                            onUsernameCommentClick = {
                                navigator.navigate(UserProfileScreenDestination)
                            },
                            onLikeClick={
                                        //TODO ON COMMENT LIKE CLICK
                            },
                            modifier=Modifier.fillMaxWidth(),
                            comment=Comment(
                                username="Stefan Aleksandric$it",
                                comment="This is very nice!"+" The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters,",
                            )
                        )
                    }
                }
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
                        navigator.navigate(UserProfileScreenDestination)
                    }
            )
        }
    }
}

@Composable
fun Comment(
    modifier:Modifier = Modifier,
    comment: Comment = Comment(),
    onLikeClick:(Boolean)->Unit = {},
    onUsernameCommentClick:()->Unit={}
){
    Card(
        modifier= modifier
            .padding(SpaceMedium)
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.onSurface),
    ){
        Column(modifier=Modifier.fillMaxSize()){
            Row(
                modifier= Modifier
                    .padding(SpaceMedium)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Row(
                    modifier=Modifier.clickable{
                        //TODO click on username in comment
                        onUsernameCommentClick()
                    }
                ){
                    Image(
                        painter= painterResource(id = R.drawable.user),
                        contentDescription=null,
                        modifier=Modifier.size(ProfilePictureSizeSmall)
                    )
                    Spacer(modifier=Modifier.width(SpaceSmall))
                    Text(
                        modifier=Modifier.align(Alignment.CenterVertically),
                        text=comment.username,
                        fontWeight = FontWeight.Bold
                    )
                }
                //TODO timestamp instead of text
                Text(
                    text="2 days ago"
                )
            }
            Row(
                modifier=Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text=comment.comment,
                    style=MaterialTheme.typography.bodyMedium,
                    modifier=Modifier.padding(SpaceSmall)
                )
            }
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ){
                    IconButton(
                        onClick = {
                            onLikeClick(comment.isLiked)
                        },
                    ){
                        Icon(imageVector=Icons.Filled.FavoriteBorder,
                            if(comment.isLiked){
                                stringResource(id=R.string.like)
                            }else stringResource(id = R.string.unlike)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text= if(comment.likeCount==1){stringResource(id = R.string.liked_by_one_person,comment.likeCount)}
                        else{stringResource(id = R.string.liked_by_x_people,comment.likeCount)},
                        fontWeight = FontWeight.Bold,
                        style=MaterialTheme.typography.bodySmall
                    )
            }
        }
    }
}

