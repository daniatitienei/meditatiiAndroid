package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.addAnnouncement.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700
import com.example.meditatii_gaseste_tiprofesorul.common.FieldType
import com.example.meditatii_gaseste_tiprofesorul.presentation.theme.MeditatiiTheme

@Composable
fun AnnouncementInputField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    type: FieldType
) {
    TextField(
        value = value,
        placeholder = { Text(placeholder) },
        onValueChange = { onValueChange(it) },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = if (type == FieldType.PRICE) KeyboardType.Number else KeyboardType.Text,
        ),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.background,
            focusedIndicatorColor = Purple700,
            cursorColor = Purple700,
        ),
    )
}

@Composable
@Preview (showBackground = true)
fun previewField() {
    MeditatiiTheme {
        AnnouncementInputField(
            value = "",
            placeholder = "Descriere",
            onValueChange = {},
            type = FieldType.DESCRIPTION
        )
    }
}