package com.locathor.brzodolokacije.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.locathor.brzodolokacije.R
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import com.locathor.brzodolokacije.presentation.ui.theme.IconSizeMedium

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardTextField(
    modifier: Modifier = Modifier,
    text: String="",
    hint:String="",
    maxLength:Int=500,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    error: String = "",
    leadingIcon: ImageVector? = null,
    keyboardType:KeyboardType= KeyboardType.Text,
    isPasswordToggleDisplayed: Boolean = keyboardType == KeyboardType.Password,
    isPasswordVisible: Boolean = false,
    onPasswordToggleClick: (Boolean) -> Unit = {},
    onValueChange:(String)->Unit
){
    Column(
        modifier = modifier
            .fillMaxWidth()
    ){
        TextField(
            value=text,
            onValueChange={
                if(it.length<=maxLength){
                    onValueChange(it)
                }
            },
            maxLines = maxLines,
            placeholder={
                Text(
                    text=hint,
                    style= MaterialTheme.typography.bodySmall
                )
            },
            isError = error != "",
            keyboardOptions= KeyboardOptions(
                keyboardType=keyboardType
            ),
            visualTransformation = if(!isPasswordVisible && isPasswordToggleDisplayed)
            {
                PasswordVisualTransformation()
            }else{ VisualTransformation.None },
            singleLine = singleLine,
            leadingIcon = if (leadingIcon != null) {
                val icon: @Composable () -> Unit = {
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = null,
                        modifier = Modifier.size(IconSizeMedium)
                    )
                }
                icon
            } else null,
            trailingIcon={
                if(isPasswordToggleDisplayed){
                    IconButton(
                        onClick={
                            onPasswordToggleClick(!isPasswordVisible)
                        },
                        modifier = Modifier
                    ){
                        Icon(imageVector = if(isPasswordVisible){
                            Icons.Filled.VisibilityOff
                        }else{
                            Icons.Filled.Visibility
                        },
                            contentDescription =if(isPasswordVisible){
                                stringResource(id = R.string.password_hidden_content_description)
                            }else{
                                stringResource(id = R.string.password_visible_content_description)
                            }
                        )
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        if (error.isNotEmpty()) {
            Text(
                text = error,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Red,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}