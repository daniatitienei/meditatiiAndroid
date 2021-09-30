package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.account.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700

@Composable
fun AccountTopBar(navController: NavController) {
    TopAppBar(
        title = { Text(text = "Cont") },
        elevation = 5.dp,
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    Icons.Rounded.ArrowBack,
                    contentDescription = null,
                    tint = Purple700
                )
            }
        },
    )
}