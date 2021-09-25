package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.InspectProfessor.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700
import com.example.meditatii_gaseste_tiprofesorul.presentation.theme.MeditatiiTheme

@Composable
fun InspectProfessorTopBar() {
    TopAppBar(
        title = {},
        elevation = 0.dp,
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    Icons.Rounded.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    Icons.Rounded.FavoriteBorder,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        backgroundColor = Purple700,
    )
}

@Composable
@Preview
fun PreviewProfessorTopBar() {
    MeditatiiTheme {
        InspectProfessorTopBar()
    }
}