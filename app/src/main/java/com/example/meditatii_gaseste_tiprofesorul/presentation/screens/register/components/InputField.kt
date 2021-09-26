package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.register.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700

@ExperimentalComposeUiApi
@Composable
fun InputField(
    value: String,
    onValueChange: (text: String) -> Unit,
    placeholder: String,
    obscureIcon: Boolean,
    emailError: String = "",
    passwordError: String = ""
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var obscureText by remember {
        mutableStateOf(true)
    }

    Column {
        TextField(
            value = value,
            onValueChange = onValueChange,
            trailingIcon =  {
                if (obscureIcon)
                    IconButton(onClick = { obscureText = !obscureText }) {
                        Icon(
                            Icons.Outlined.Clear,
                            contentDescription = null,
                            tint = Purple700
                        )
                        //                TODO Bag iconita cu ochi
                    }
            },
            visualTransformation = if (obscureIcon && obscureText) PasswordVisualTransformation() else VisualTransformation.None,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.background,
                focusedIndicatorColor = Purple700,
                cursorColor = Purple700,
            ),
            placeholder = { Text(text = placeholder) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            ),
            isError = passwordError.isNotBlank() || emailError.isNotBlank()
        )
        if (emailError.isNotBlank()) Text(text = emailError, style = TextStyle(color = Color.Red, fontSize = 13.sp))
        if (passwordError.isNotBlank()) Text(text = passwordError, style = TextStyle(color = Color.Red, fontSize = 13.sp))
    }
    
}