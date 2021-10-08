package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.InspectProfessor.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700
import com.example.meditatii_gaseste_tiprofesorul.domain.model.Professor
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.InspectProfessor.InspectProfessorViewModel

@Composable
fun InspectProfessorTopBar(
    navController: NavController,
    inspectProfessorViewModel: InspectProfessorViewModel = hiltViewModel(),
    professor: Professor
) {
    inspectProfessorViewModel.checkFavorite(professor)

    TopAppBar(
        title = {},
        elevation = 0.dp,
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(
                    Icons.Rounded.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        actions = {
            IconButton(onClick = {
                if (inspectProfessorViewModel.favorite.value)
                    inspectProfessorViewModel.removeFavorite(professor = professor)
                else
                    inspectProfessorViewModel.addFavorite(professor = professor)
            }) {
                Icon(
                    if (!inspectProfessorViewModel.favorite.value) Icons.Rounded.FavoriteBorder else Icons.Rounded.Favorite,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        backgroundColor = Purple700,
    )
}
