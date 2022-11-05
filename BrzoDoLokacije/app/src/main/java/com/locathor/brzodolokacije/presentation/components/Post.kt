package com.locathor.brzodolokacije.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.locathor.brzodolokacije.R
import com.locathor.brzodolokacije.domain.model.Post
import com.locathor.brzodolokacije.ui.theme.*
import com.locathor.brzodolokacije.util.Constants

@Composable
fun Post(
    post:Post,
){
    Box(
        modifier= Modifier
            .fillMaxWidth()
            .padding(SpaceSmall)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
                .shadow(3.dp)
                .padding(SpaceMedium),
        ){
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
                )
            }
            ActionRow(
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
                text=
                buildAnnotatedString {
                    append(post.desc)
                    withStyle(SpanStyle(
                        color= HintGray
                    ))      {
                        append(LocalContext.current.getString(R.string.read_more))
                    }

                },
                style = MaterialTheme.typography.bodyMedium,
                overflow=TextOverflow.Ellipsis,
                maxLines=Constants.MAX_POST_DESCRIPTION_LINES
            )
            Spacer(modifier = Modifier.height(SpaceSmall))
            Row(
                modifier=Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text= stringResource(id = R.string.liked_by_x_people,post.likeCount),
                    style=MaterialTheme.typography.titleSmall
                )
                Text(
                    text= stringResource(id = R.string.x_comments,post.commentCount),
                    style=MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}

@Composable
fun EngagementButtons(
    onLikeClick:(Boolean)->Unit={},
    onCommentClick:()->Unit={},
    onShareClick:()->Unit={},
    isLiked:Boolean=false,
    iconSize: Dp = 30.dp,
){
    Row(
        horizontalArrangement = Arrangement.SpaceAround
    ){
        IconButton(onClick={
            onLikeClick(!isLiked)
        }, modifier = Modifier.size(iconSize)
        ){
            Icon(
                imageVector = if(isLiked){
                    Icons.Filled.Favorite
                }else Icons.Filled.FavoriteBorder,
                tint = if(isLiked){
                    Color.Red
                }else Color.Black,
                contentDescription = if(isLiked){
                    stringResource(id = R.string.unlike)
                } else stringResource(id = R.string.like)
            )
        }
        Spacer(modifier=Modifier.width(SpaceMedium))
        IconButton(onClick={
            onCommentClick()
        },modifier = Modifier.size(iconSize)){
            Icon(
                Icons.Filled.Comment,
                contentDescription = stringResource(id = R.string.comment)
            )
        }
        Spacer(modifier=Modifier.width(SpaceMedium))
        IconButton(onClick={
            onShareClick()
        },modifier = Modifier.size(iconSize)){
            Icon(
                Icons.Filled.Share,
                contentDescription = stringResource(id = R.string.share)
            )
        }
    }
}

@Composable
fun ActionRow(
    modifier:Modifier=Modifier,
    isLiked:Boolean=false,
    onLikeClick:(Boolean)->Unit={},
    onCommentClick:()->Unit={},
    onLocationClick:()->Unit={},
    onShareClick:()->Unit={},
    username:String,
    onUsernameClick:(String)->Unit={}
){
    Row(
        modifier=modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Row(
            modifier= Modifier
                .clickable{
                    onUsernameClick(username)
                }
        ){
            Image(
                //TODO pull postOwner image
                painterResource(id = R.drawable.user),
                contentDescription = "Profile picture",
                modifier=Modifier
                    .clip(CircleShape)
                    .size(30.dp)
            )
            Spacer(modifier=Modifier.width(SpaceSmall))
            Text(
                text=username,
                style= MaterialTheme.typography.headlineSmall,
            )
        }
        EngagementButtons(
            isLiked = isLiked,
            onLikeClick = onLikeClick,
            onCommentClick = onCommentClick,
            onShareClick = onShareClick,
        )
    }
}