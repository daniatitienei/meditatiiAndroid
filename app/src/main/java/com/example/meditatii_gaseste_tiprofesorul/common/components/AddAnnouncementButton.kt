package com.example.meditatii_gaseste_tiprofesorul.common.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700
import com.example.meditatii_gaseste_tiprofesorul.common.Screens

@Composable
fun AddAnnouncementButton(navController: NavController) {
    FloatingActionButton(
        onClick = {
            navController.navigate(Screens.AddAnnouncement.route)
        },
        shape = CircleShape,
        content = {
            Icon(Icons.Rounded.Add, contentDescription = null, tint = Color.White)
        },
        backgroundColor = Purple700,
    )
}