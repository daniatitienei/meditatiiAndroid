package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.selectedCategory.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700
import com.example.meditatii_gaseste_tiprofesorul.common.Screens

@Composable
fun SelectedCategoryTopBar(navController: NavController, title: String) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(Icons.Rounded.ArrowBack, contentDescription = null, tint = Purple700)
            }
        },
        actions = {
            IconButton(onClick = {
                navController.navigate(Screens.Filters.route) { launchSingleTop = true }
            }) {
                Icon(Icons.Rounded.Menu, contentDescription = null, tint = Purple700)
            }
        }
    )
}