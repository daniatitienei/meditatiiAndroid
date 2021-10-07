package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.createProfessorProfile.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700

@ExperimentalComposeUiApi
@Composable
fun SearchTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = placeholder) },
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = Purple700,
            backgroundColor = Color(0xffe8e8e6),
            leadingIconColor = Purple700,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clip(CircleShape),
        leadingIcon = {
            Icon(Icons.Rounded.Search, contentDescription = null)
//                            Todo sa apara un x ca sa stearga tot
        },
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            }
        ),
    )
    Spacer(modifier = Modifier.height(20.dp))
}