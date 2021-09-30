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
import com.example.meditatii_gaseste_tiprofesorul.common.FieldType
import com.example.meditatii_gaseste_tiprofesorul.common.PhoneNumberVisualTransformation

@ExperimentalComposeUiApi
@Composable
fun InputField(
    value: String,
    onValueChange: (text: String) -> Unit,
    placeholder: String,
    error: String = "",
    type: FieldType
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
                if (type == FieldType.PASSWORD)
                    IconButton(onClick = { obscureText = !obscureText }) {
                        Icon(
                            Icons.Outlined.Clear,
                            contentDescription = null,
                            tint = Purple700
                        )
                        //                TODO Bag iconita cu ochi
                    }
            },
            visualTransformation = when(type) {
                FieldType.PASSWORD -> PasswordVisualTransformation()
                FieldType.PHONE_NUMBER -> PhoneNumberVisualTransformation()
                else -> VisualTransformation.None
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.background,
                focusedIndicatorColor = Purple700,
                cursorColor = Purple700,
            ),
            placeholder = { Text(text = placeholder) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = type != FieldType.DESCRIPTION,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                autoCorrect = type == FieldType.PASSWORD
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            ),
            isError = error.isNotBlank()
        )
        if (error.isNotBlank()) Text(text = error, style = TextStyle(color = Color.Red, fontSize = 13.sp))
    }
    
}