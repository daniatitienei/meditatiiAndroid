package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.selectedCategory.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700
import com.example.meditatii_gaseste_tiprofesorul.common.Screens
import com.free.find_your_teacher.R

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
                Icon(painter = painterResource(id = R.drawable.ic_baseline_filter_alt_24), contentDescription = null, tint = Purple700)
            }
        }
    )
}