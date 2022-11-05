package com.locathor.brzodolokacije.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.locathor.brzodolokacije.ui.theme.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardBottomBar(
    modifier: Modifier=Modifier,
    bottomBarOn:Boolean=true,
    content:@Composable () -> Unit)
{
    Scaffold (
            bottomBar = {
                if(bottomBarOn){
                BottomAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    contentColor = Color.White,
                    containerColor = Color.Blue,
                    actions = {
                            Spacer(Modifier.size(SpaceLarge))
                            IconButton(onClick = {
                                /* doSomething() */
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
                                /* doSomething() */
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
                                /* doSomething() */
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
                                /* doSomething() */
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
                                onClick = { /* do something */ },
                                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                            ) {
                                Icon(Icons.Filled.Add, "Localized description")
                            }
                    }
                )}
            },
            modifier = Modifier
        ){
        content()
    }
}