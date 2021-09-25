package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.InspectProfessor.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700

@Composable
fun ContactTile(text: String, isPhone: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .clickable {  },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            if (isPhone) Icons.Rounded.Phone else Icons.Rounded.Email,
            contentDescription = null,
            tint = Purple700,
            modifier = Modifier.size(26.dp)
        )

        Spacer(modifier = Modifier.width(15.dp))

        Text(text = text)
    }
}