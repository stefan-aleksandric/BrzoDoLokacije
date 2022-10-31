package com.locathor.brzodolokacije.presentation.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.locathor.brzodolokacije.presentation.components.StandardTextField
import com.locathor.brzodolokacije.presentation.register.RegisterState
import com.locathor.brzodolokacije.ui.theme.SpaceLarge
import com.locathor.brzodolokacije.ui.theme.SpaceMedium
import com.locathor.brzodolokacije.util.Constants
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val scrollState = rememberScrollState()
    Box(modifier= Modifier
        .fillMaxSize()
        .padding(
            start = SpaceLarge,
            end = SpaceLarge,
            top = SpaceLarge,
            bottom = 50.dp
        )
        .scrollable(state=scrollState, orientation = Orientation.Horizontal)
        .scrollable(state=scrollState, orientation = Orientation.Vertical)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(SpaceMedium)
                .align(Alignment.Center)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = stringResource(id = com.locathor.brzodolokacije.R.string.login),
                color = Color.Black,
                style = MaterialTheme.typography.headlineLarge
                )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                text = viewModel.state.username,
                onValueChange = {
                    viewModel.setUsernameText(it)
                },
                keyboardType = KeyboardType.Email,
                error = when (state.usernameError) {
                    LoginState.UsernameError.FieldEmpty -> {
                        stringResource(id = com.locathor.brzodolokacije.R.string.this_field_cant_be_empty)
                    }
                    null -> ""
                },
                hint = stringResource(id = com.locathor.brzodolokacije.R.string.login_hint)
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                text = state.password,
                onValueChange = {
                    viewModel.setPasswordText(it)
                },
                hint = stringResource(id = com.locathor.brzodolokacije.R.string.password_hint),
                keyboardType = KeyboardType.Password,
                error = when (state.passwordError) {
                    LoginState.PasswordError.FieldEmpty -> {
                        stringResource(id = com.locathor.brzodolokacije.R.string.this_field_cant_be_empty)
                    }
                    null -> ""
                },
                isPasswordVisible=viewModel.showPassword.value,
                onPasswordToggleClick={
                    viewModel.setShowPassword(it)
                }
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            Button(
                onClick = {
                    //TODO on button press navigate
                          viewModel.onEvent(LoginEvent.OnLoginButtonPress)
                },
                modifier = Modifier
                    .align(Alignment.End)
            ) {
                Text(
                    text = stringResource(id = com.locathor.brzodolokacije.R.string.login),
                    color = Color.Green
                )
            }
            Spacer(modifier = Modifier.height(SpaceLarge))
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
                    .align(alignment = Alignment.CenterHorizontally)
                //clickable {
                //TODO navigation for already have an account
                //DestinationsNavigator.navigate()
                //}
            )
        }

    }
}

