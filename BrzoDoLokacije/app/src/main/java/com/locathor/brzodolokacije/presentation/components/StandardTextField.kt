package com.locathor.brzodolokacije.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardTextField(
    text: String="",
    hint:String="",
    maxLength:Int=50,
    isError:Boolean=false,
    keyboardType:KeyboardType= KeyboardType.Text,
    onValueChange:(String)->Unit
){
    val isPasswordToggleDisplayed by remember{
        mutableStateOf(keyboardType==KeyboardType.Password)
    }
    var isPasswordVisible by remember {
        mutableStateOf(false)
    }

    TextField(
        value=text,
        onValueChange={
                      if(it.length<=maxLength){
                          onValueChange(it)
                      }
        },
        placeholder={
            Text(
                text=hint,
                style= MaterialTheme.typography.bodySmall
            )
        },
        isError=isError,
        keyboardOptions= KeyboardOptions(
            keyboardType=keyboardType
        ),
        visualTransformation = if(isPasswordVisible && isPasswordToggleDisplayed)
        {
            PasswordVisualTransformation()
        }else{ VisualTransformation.None },
        singleLine=true,
        //TODO visibilityICON
        /*trailingIcon={
            if(isPasswordToggleDisplayed){
                IconButton(
                    onClick={
                        isPasswordVisible=!isPasswordVisible
                    }){
                    Icon(imageVector = if(isPasswordVisible){
                        Icons.Filled.VisibilityOff
                    }else{
                         Icons.Filled.Visibility
                         },
                        contentDescription =if(isPasswordVisible){
                            stringResource(id = )
                        }else{
                            stringResource(id = )
                        }
                    )
                }
            }
        },*/
        modifier = Modifier
            .fillMaxWidth()
    )
}