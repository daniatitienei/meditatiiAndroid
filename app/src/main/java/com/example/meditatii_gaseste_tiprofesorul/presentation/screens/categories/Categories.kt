package com.example.meditatii_gaseste_tiprofesorul.presentation.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.example.meditatii_gaseste_tiprofesorul.common.Screens
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.categories.CategoriesViewModel
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.categories.components.CategoriesAppBar
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.categories.components.CategoryTileList
import com.example.meditatii_gaseste_tiprofesorul.presentation.theme.MeditatiiTheme

@ExperimentalCoilApi
@Composable
fun Categories(
    navController: NavController,
    categoriesViewModel: CategoriesViewModel,
) {
    Scaffold(
        topBar = { CategoriesAppBar() }
    ) {
        LazyColumn {
            items(categoriesViewModel.categoriesList.size, itemContent = {
                val materie = categoriesViewModel.categoriesList[it]

                CategoryTileList(
                    onClick = {
                        navController.navigate(
                            Screens.SelectedCategory.route.replace("{numeMaterie}", materie.nume),
                        )
                    },
                    materie = materie,
                )
                Spacer(modifier = Modifier.height(10.dp))
            })
        }
    }
}

@Preview
@Composable
fun CategoriesPreview() {
    MeditatiiTheme {
        CategoriesAppBar()
    }
}