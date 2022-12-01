package com.locathor.brzodolokacije.presentation.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.locathor.brzodolokacije.presentation.ui.theme.SpaceLarge
import com.locathor.brzodolokacije.presentation.ui.theme.SpaceSmall
import com.locathor.brzodolokacije.presentation.ui.theme.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardScaffold(
    modifier: Modifier=Modifier,
    bottomBarOn:Boolean=true,
    topBarOn:Boolean=true,
    searchOn:Boolean=true,
    commentInputOn:Boolean=true,
    navigationArrowOn:Boolean=true,
    onArrowNavigationClick:()->Unit={},
    onHomeButtonClick:()->Unit={},
    onUserProfileClick:()->Unit={},
    onActivityClick:()->Unit={},
    onAddButtonClick:()->Unit={},
    onSearchClick:()->Unit={},
    onInboxClick:()->Unit={},
    commentInputText:String="",
    toolbarTitle:String?=null,
    onCommentInsertChange:(String)->Unit={},
    content:@Composable () -> Unit)
{
    Scaffold (
        topBar = {
            if(topBarOn){
                CenterAlignedTopAppBar(
                title = {
                    if(toolbarTitle!=null){
                        Text(
                            text=toolbarTitle,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )}
                },
                navigationIcon = {
                    if(navigationArrowOn){
                        IconButton(onClick = {
                            onArrowNavigationClick()
                        }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Go Back Icon"
                            )
                        }}
                },
                actions = {
                    if(searchOn){
                    IconButton(onClick = {
                        onSearchClick()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search"
                        )
                    }}
                }
            ) }
        },
            bottomBar = {
                if(bottomBarOn){
                BottomAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    contentColor = Color.White,
                    containerColor = MaterialTheme.colorScheme.primary,
                    actions = {
                            Spacer(Modifier.size(SpaceLarge))
                            IconButton(onClick = {
                                onHomeButtonClick()
                            }) {
                                Icon(
                                    Icons.Filled.Home,
                                    contentDescription = "Home",
                                    modifier = Modifier
                                        .size(128.dp)
                                )
                            }
                            Spacer(Modifier.size(SpaceLarge + SpaceSmall))
                            IconButton(onClick = {
                                onInboxClick()
                            }) {
                                Icon(
                                    Icons.Filled.Message,
                                    contentDescription = "Inbox",
                                    modifier = Modifier
                                        .size(128.dp)
                                )
                            }
                            Spacer(Modifier.size(SpaceLarge + SpaceSmall))
                            IconButton(onClick = {
                                onActivityClick()
                            }) {
                                Icon(
                                    Icons.Filled.Notifications,
                                    contentDescription = "Notifications",
                                    modifier = Modifier
                                        .size(128.dp)
                                )
                            }
                            Spacer(Modifier.size(SpaceLarge + SpaceSmall))
                            IconButton(onClick = {
                                onUserProfileClick()
                            }) {
                                Icon(
                                    Icons.Filled.Person,
                                    contentDescription = "User profile",
                                    modifier = Modifier
                                        .size(128.dp)
                                )
                            }
                    },
                    floatingActionButton = {
                            FloatingActionButton(
                                shape = CircleShape,
                                onClick = {
                                            onAddButtonClick()
                                          },
                                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                            ) {
                                Icon(Icons.Filled.Add, "Localized description")
                            }
                    }
                )}else if(commentInputOn){
                    BottomAppBar(
                        modifier = Modifier.fillMaxWidth(),
                        contentColor = Color.White,
                        containerColor = MaterialTheme.colorScheme.primary,
                        actions = {
                            TextField(
                                value = commentInputText ,
                                onValueChange ={
                                    onCommentInsertChange(it)
                                },
                                placeholder = {
                                    Text(
                                        text="Input your comment here...",
                                        style= MaterialTheme.typography.bodySmall
                                    )
                                },
                            )
                        },
                        floatingActionButton = {
                            FloatingActionButton(
                                shape = CircleShape,
                                onClick = {
                                          //TODO add comment button
                                          },
                                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                            ) {
                                Icon(Icons.Filled.Input, "Localized description")
                            }
                        }
                    )
                }
            },
            modifier = Modifier
        ){ innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)){
            content()
        }
    }
}