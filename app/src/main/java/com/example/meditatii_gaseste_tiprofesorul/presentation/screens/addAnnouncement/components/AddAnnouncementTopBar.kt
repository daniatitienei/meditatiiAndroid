package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.addAnnouncement.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700
import com.example.meditatii_gaseste_tiprofesorul.presentation.theme.MeditatiiTheme

@Composable
fun AddAnnouncementTopBar(navController: NavController) {
    TopAppBar(
        title = { Text("Adauga un anunt") },
        backgroundColor = Color.White,
        elevation = 0.dp,
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(Icons.Rounded.Close, contentDescription = null, tint = Purple700)
            }
        }
    )
}

@Composable
@Preview
fun previewBar() {
    MeditatiiTheme {
        AddAnnouncementTopBar(rememberNavController())
    }
}