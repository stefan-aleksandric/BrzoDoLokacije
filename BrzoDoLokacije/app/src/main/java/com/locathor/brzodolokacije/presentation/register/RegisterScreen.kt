package com.locathor.brzodolokacije.presentation.register

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.locathor.brzodolokacije.presentation.login.LoginEvent
import com.locathor.brzodolokacije.ui.theme.SpaceMedium
import com.locathor.brzodolokacije.util.Constants

//@RootNavGraph(start = true)
@Destination
@Composable
fun RegisterScreen(
    navigator: DestinationsNavigator,
    viewModel: RegisterViewModel= hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val state = viewModel.state
    Box(
        modifier = Modifier
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
                .align(Alignment.Center)
                .verticalScroll(rememberScrollState())
        ) {
            val context= LocalContext.current
            Text(
                text = stringResource(id = com.locathor.brzodolokacije.R.string.register),
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                text = state.email,
                onValueChange = {
                    viewModel.setEmailText(it)
                },
                error = when (state.emailError) {
                    RegisterState.EmailError.FieldEmpty -> {
                        stringResource(id = com.locathor.brzodolokacije.R.string.this_field_cant_be_empty)
                    }
                    RegisterState.EmailError.InvalidEmail -> {
                        stringResource(id = com.locathor.brzodolokacije.R.string.not_a_valid_email)
                    }
                    null -> ""
                },
                keyboardType = KeyboardType.Email,
                hint = stringResource(com.locathor.brzodolokacije.R.string.email)
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                text = state.username,
                onValueChange = {
                    viewModel.setUsernameText(it)
                },
                error = when (state.usernameError) {
                    RegisterState.UsernameError.FieldEmpty -> {
                        stringResource(id = com.locathor.brzodolokacije.R.string.this_field_cant_be_empty)
                    }
                    RegisterState.UsernameError.InputTooShort -> {
                        stringResource(id = com.locathor.brzodolokacije.R.string.input_too_short, Constants.MIN_PASSWORD_LENGTH)
                    }
                    null -> ""
                },
                hint = stringResource(id = com.locathor.brzodolokacije.R.string.username)
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                text = state.name,
                onValueChange = {
                    viewModel.setNameText(it)
                },
                error = when (state.nameError) {
                    RegisterState.NameError.FieldEmpty -> {
                        stringResource(id = com.locathor.brzodolokacije.R.string.this_field_cant_be_empty)
                    }
                    RegisterState.NameError.InputTooShort -> {
                        stringResource(id = com.locathor.brzodolokacije.R.string.input_too_short, Constants.MIN_PASSWORD_LENGTH)
                    }
                    null -> ""
                },
                hint = stringResource(id = com.locathor.brzodolokacije.R.string.name)
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                text = state.surname,
                onValueChange = {
                    viewModel.setSurnameText(it)
                },
                error = when (state.surnameError) {
                    RegisterState.SurnameError.FieldEmpty -> {
                        stringResource(id = com.locathor.brzodolokacije.R.string.this_field_cant_be_empty)
                    }
                    RegisterState.SurnameError.InputTooShort -> {
                        stringResource(id = com.locathor.brzodolokacije.R.string.input_too_short, Constants.MIN_PASSWORD_LENGTH)
                    }
                    null -> ""
                },
                hint = stringResource(id = com.locathor.brzodolokacije.R.string.surname)
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
                    RegisterState.PasswordError.FieldEmpty -> {
                        stringResource(id = com.locathor.brzodolokacije.R.string.this_field_cant_be_empty)
                    }
                    RegisterState.PasswordError.InputTooShort -> {
                        stringResource(id = com.locathor.brzodolokacije.R.string.input_too_short, Constants.MIN_PASSWORD_LENGTH)
                    }
                    RegisterState.PasswordError.InvalidPassword -> {
                        stringResource(id = com.locathor.brzodolokacije.R.string.invalid_password)
                    }
                    null -> ""
                },
                isPasswordVisible = state.isPasswordVisible,
                onPasswordToggleClick = {
                    viewModel.onEvent(RegisterEvent.TogglePasswordVisibility)
                }
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            Button(
                onClick = {
                    viewModel.onEvent(RegisterEvent.OnRegisterButtonPress)
                    Toast.makeText(context,"Attempting registration...",Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .align(Alignment.End)
            ) {
                Text(
                    text = stringResource(id = com.locathor.brzodolokacije.R.string.register),
                    color = Color.Green
                )
            }
            Spacer(modifier = Modifier.height(SpaceLarge))
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
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    //.align(Alignment.BottomCenter)
                    .align(alignment = Alignment.CenterHorizontally)
                //.clickable {
                //TODO navigation for don't have an account yet
                //}
            )
        }
    }
}


