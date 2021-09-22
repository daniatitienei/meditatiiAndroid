package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.categories.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700
import com.example.meditatii_gaseste_tiprofesorul.presentation.theme.MeditatiiTheme

@Composable
fun CategoryTileList() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 15.dp, vertical = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row {
            Box() {

            }

            Column {
                Text(text = "Numele materiei")
                Spacer(modifier = Modifier.height(3.dp))
                Text(text = "nr anunturi")
            }
        }
        IconButton(onClick = { /* TODO */ }) {
            Icon(
                Icons.Rounded.ArrowForward,
                contentDescription = null,
                tint = Purple700
            )
        }
    }
}

@Preview
@Composable
fun PreviewTile() {
    MeditatiiTheme {
        CategoryTileList()
    }
}