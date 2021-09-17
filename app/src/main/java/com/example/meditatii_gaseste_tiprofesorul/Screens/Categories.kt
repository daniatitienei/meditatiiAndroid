package com.example.meditatii_gaseste_tiprofesorul.Screens

import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700
import com.example.meditatii_gaseste_tiprofesorul.theme.MeditatiiTheme

@Composable
fun Categories() {
    Scaffold(
        topBar = { CategoriesAppBar() }
    ) {

    }
}

@Composable
fun CategoriesAppBar() {
    TopAppBar(
        title = { Text(text = "Materii") },
        elevation = 5.dp,
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    Icons.Rounded.Person,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp),
                    tint = Purple700
                )
            }
        }
    )
}

@Preview
@Composable
fun CategoriesPreview() {
    MeditatiiTheme {
        CategoriesAppBar()
    }
}