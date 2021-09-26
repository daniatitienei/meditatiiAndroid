package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.categories.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700
import com.example.meditatii_gaseste_tiprofesorul.common.Screens
import com.google.firebase.auth.FirebaseAuth

@Composable
fun CategoriesAppBar(navController: NavController, auth: FirebaseAuth) {
    TopAppBar(
        title = { Text(text = "Materii") },
        elevation = 5.dp,
        actions = {
            IconButton(onClick = {
                auth.signOut()
                navController.navigate(Screens.Register.route) {
                    launchSingleTop = true
                }
            }) {
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