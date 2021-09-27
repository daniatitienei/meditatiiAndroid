package com.example.meditatii_gaseste_tiprofesorul.ui.screens.account.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700

@Composable
fun TextListTile(text: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .clickable { onClick() }
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = text)
        Spacer(modifier = Modifier.height(10.dp))
        Divider(
            color = Purple700,
            thickness = 1.dp,
        )
    }
}