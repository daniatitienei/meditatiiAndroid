package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.register.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700

@ExperimentalComposeUiApi
@Composable
fun InputField(
    value: String,
    onValueChange: (text: String) -> Unit,
    placeholder: String,
    obscureIcon: Boolean,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var obscureText by remember {
        mutableStateOf(true)
    }

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
    )
}