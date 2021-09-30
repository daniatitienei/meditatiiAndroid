package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.newProfessor

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700
import com.example.meditatii_gaseste_tiprofesorul.common.PhoneNumberVisualTransformation

@Composable
fun PhoneField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
) {
    TextField(
        value = value,
        placeholder = { Text(placeholder) },
        onValueChange = { onValueChange(it) },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Number
        ),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.background,
            focusedIndicatorColor = Purple700,
            cursorColor = Purple700,
        ),
        leadingIcon = {
            Text("+40")
        },
        visualTransformation = PhoneNumberVisualTransformation()
    )
}