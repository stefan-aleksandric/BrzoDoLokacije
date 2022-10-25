package com.locathor.brzodolokacije.presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.locathor.brzodolokacije.presentation.components.StandardTextField
import com.locathor.brzodolokacije.ui.theme.SpaceLarge
import com.locathor.brzodolokacije.ui.theme.SpaceMedium
import com.locathor.brzodolokacije.ui.theme.SpaceSmall
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator,
    viewModel: LoginViewModel = hiltViewModel()
) {
    Box(modifier= Modifier
        .fillMaxSize()
        .padding(
            start = SpaceLarge,
            end = SpaceLarge,
            top = SpaceLarge,
            bottom = 50.dp
        )
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(SpaceMedium)
                .align(Alignment.Center),
        ) {
            Text(
                text = stringResource(id = com.locathor.brzodolokacije.R.string.login),
                color = Color.Black,
                style = MaterialTheme.typography.headlineLarge
                )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                text = viewModel.usernameText.value,

                onValueChange = {
                    viewModel.setUsernameText(it)
                },
                hint = stringResource(id = com.locathor.brzodolokacije.R.string.login_hint)
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                text = viewModel.passwordText.value,
                onValueChange = {
                    viewModel.setPasswordText(it)
                },
                hint = stringResource(id = com.locathor.brzodolokacije.R.string.password_hint),
                keyboardType = KeyboardType.Password
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            Button(
                onClick = {
                    //TODO navigation after login
                },
                modifier = Modifier
                    .align(Alignment.End)
            ) {
                Text(
                    text = stringResource(id = com.locathor.brzodolokacije.R.string.login),
                    color = Color.Green
                )
            }

        }
        Text(
            text = buildAnnotatedString {
                append(stringResource(id = com.locathor.brzodolokacije.R.string.dont_have_an_account_yet))
                append(" ")
                withStyle(style=SpanStyle(
                    color=Color.Green
                )){
                    append(stringResource(id= com.locathor.brzodolokacije.R.string.sign_up))
                }
            },
            style=MaterialTheme.typography.bodyLarge,
            modifier=Modifier
                .align(Alignment.BottomCenter)
        )
    }
}

