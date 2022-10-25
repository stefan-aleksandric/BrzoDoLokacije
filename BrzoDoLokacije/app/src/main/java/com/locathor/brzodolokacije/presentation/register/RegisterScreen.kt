package com.locathor.brzodolokacije.presentation.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.locathor.brzodolokacije.ui.theme.SpaceLarge
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.locathor.brzodolokacije.presentation.components.StandardTextField
import com.locathor.brzodolokacije.ui.theme.SpaceMedium

@RootNavGraph(start = true)
@Destination
@Composable
fun RegisterScreen(
    navigator: DestinationsNavigator,
    viewModel: RegisterViewModel= hiltViewModel()
) {
    val state = viewModel.state.value
    Box(
        modifier = Modifier
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
                .align(Alignment.Center),
        ) {
            Text(
                text = stringResource(id = com.locathor.brzodolokacije.R.string.register),
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                text = state.email,
                onValueChange = {
                    viewModel.onEvent(RegisterEvent.EnteredEmail(it))
                },
                //TODO email error
                keyboardType = KeyboardType.Email,
                hint = stringResource(com.locathor.brzodolokacije.R.string.email)
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                text = state.username,
                onValueChange = {
                    viewModel.onEvent(RegisterEvent.EnteredUsername(it))
                },
                //TODO username error
                hint = stringResource(id = com.locathor.brzodolokacije.R.string.username)
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                text = state.name,
                onValueChange = {
                    viewModel.onEvent(RegisterEvent.EnteredName(it))
                },
                //TODO name error
                hint = stringResource(id = com.locathor.brzodolokacije.R.string.name)
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                text = state.surname,
                onValueChange = {
                    viewModel.onEvent(RegisterEvent.EnteredSurname(it))
                },
                //TODO surname error
                hint = stringResource(id = com.locathor.brzodolokacije.R.string.surname)
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                text = state.password,
                onValueChange = {
                    viewModel.onEvent(RegisterEvent.EnteredPassword(it))
                },
                hint = stringResource(id = com.locathor.brzodolokacije.R.string.password_hint),
                keyboardType = KeyboardType.Password,
                //TODO password error
                //TODO Password visibility
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            Button(
                onClick = {
                    viewModel.onEvent(RegisterEvent.Register)
                },
                modifier = Modifier
                    .align(Alignment.End)
            ) {
                Text(
                    text = stringResource(id = com.locathor.brzodolokacije.R.string.register),
                    color = Color.Green
                )
            }
        }
        Text(
            text = buildAnnotatedString {
                append(stringResource(id = com.locathor.brzodolokacije.R.string.already_have_an_account))
                append(" ")
                val signUpText = stringResource(id = com.locathor.brzodolokacije.R.string.sign_in)
                withStyle(
                    style = SpanStyle(
                        color=Color.Green
                    )
                ) {
                    append(signUpText)
                }
            },
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .clickable {
                    //TODO navigation to main screen
                }
        )
    }
}


