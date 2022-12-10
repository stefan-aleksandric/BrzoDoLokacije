package com.locathor.brzodolokacije.presentation.components

import android.location.Geocoder
import android.location.Geocoder.GeocodeListener
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import androidx.core.content.ContextCompat
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.compose.ImagePainter
import com.locathor.brzodolokacije.R
import com.locathor.brzodolokacije.domain.model.Post
import com.locathor.brzodolokacije.presentation.ui.theme.SpaceMedium
import com.locathor.brzodolokacije.presentation.ui.theme.SpaceSmall
import com.locathor.brzodolokacije.presentation.ui.theme.*
import com.locathor.brzodolokacije.util.Constants
import java.util.*

@Composable
fun Post(
    post: Post,
    onPostClick: ()->Unit,
    onUsernameClick: (String)->Unit={}
){
    Box(
        modifier= Modifier
            .fillMaxWidth()
            .padding(SpaceSmall)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.small)
                .shadow(4.dp)
                .padding(SpaceSmall),
        ){
            Column(
                modifier=Modifier.fillMaxWidth()
            ){
                Text(
                    text=post.title,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier=Modifier.padding(start= SpaceSmall)
                )
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
                        text= "",
                        style=MaterialTheme.typography.titleSmall
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onPostClick()
                    }
            ){
                AsyncImage(
                        model = post.mediaUris.first(),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp)
                    )
            }
            ActionRow(
                username=post.ownerUsername,
                modifier=Modifier.fillMaxWidth(),
                onLikeClick = {

                },
                onCommentClick = {
                    onPostClick()
                },
                onShareClick = {

                },
                onUsernameClick = {
                    //TODO SEND USERNAME
                    onUsernameClick("Pera Peric")
                }
            )
            Spacer(modifier = Modifier.height(SpaceSmall))
            Text(
                text=post.desc,
                style = MaterialTheme.typography.bodySmall,
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
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier=Modifier
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
    userIconOn:Boolean=true,
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
            if(userIconOn){
            Image(
                //TODO pull postOwner image
                painterResource(id = R.drawable.user),
                contentDescription = "Profile picture",
                modifier= Modifier
                    .clip(CircleShape)
                    .size(IconSizeLarge)
            )
            }
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