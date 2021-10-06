package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.register.components

import android.util.Log
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.input.KeyboardType
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
    onValueChange: (text: String) -> Unit = {},
    placeholder: String,
    error: String = "",
    leadingIcon: @Composable() (() -> Unit)? = null,
    type: FieldType,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
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
                when (type) {
                    FieldType.PASSWORD -> IconButton(onClick = { obscureText = !obscureText }) {
                        Icon(
                            Icons.Outlined.Clear,
                            contentDescription = null,
                            tint = Purple700
                        )
                        //                TODO Bag iconita cu ochi
                    }
                    FieldType.PRICE -> Text(text = "Lei")
                    else -> null
                }
            },
            leadingIcon = leadingIcon,
            visualTransformation = when(type) {
                FieldType.PASSWORD -> if (obscureText) PasswordVisualTransformation() else VisualTransformation.None
                FieldType.PHONE_NUMBER -> PhoneNumberVisualTransformation()
                else -> VisualTransformation.None
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.background,
                focusedIndicatorColor = Purple700,
                cursorColor = Purple700,
            ),
            modifier = myModifier(type, enabled, onClick),
            placeholder = { Text(text = placeholder) },
            maxLines = if (type == FieldType.DESCRIPTION) 5 else 1,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                autoCorrect = type == FieldType.PASSWORD,
                keyboardType = when(type) {
                    FieldType.EMAIL -> KeyboardType.Email
                    FieldType.PASSWORD -> KeyboardType.Password
                    FieldType.PHONE_NUMBER -> KeyboardType.Phone
                    FieldType.PRICE -> KeyboardType.Number
                    else -> KeyboardType.Text
                }
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            ),
            enabled = enabled,
            isError = error.isNotBlank()
        )
        if (error.isNotBlank()) Text(text = error, style = TextStyle(color = Color.Red, fontSize = 13.sp))
    }
}

fun myModifier(type: FieldType, enabled: Boolean, onClick: () -> Unit): Modifier {
    Log.d("este", enabled.toString())
    return if (type == FieldType.PASSWORD || type == FieldType.EMAIL)
        Modifier.fillMaxWidth()
    else if (!enabled)
        Modifier.clickable { onClick() }
    else Modifier
}