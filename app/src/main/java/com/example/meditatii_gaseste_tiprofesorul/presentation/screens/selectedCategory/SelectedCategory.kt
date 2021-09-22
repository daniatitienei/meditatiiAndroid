package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.selectedCategory

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.meditatii_gaseste_tiprofesorul.domain.model.Materie
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.selectedCategory.components.SelectedCategoryTopBar
import com.example.meditatii_gaseste_tiprofesorul.presentation.theme.MeditatiiTheme

@Composable
fun SelectedCategory(
    navController: NavController = rememberNavController(),
    materie: Materie = Materie()
) {
    Scaffold(
        topBar = { SelectedCategoryTopBar(
            navController = navController,
            title = materie.nume
        ) }
    ) {

    }
}

@Composable
@Preview
fun previewSelectedCategory() {
    MeditatiiTheme {
        SelectedCategory()
    }
}
