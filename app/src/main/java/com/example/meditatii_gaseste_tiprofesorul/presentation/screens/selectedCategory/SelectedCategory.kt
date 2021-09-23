package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.selectedCategory

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.selectedCategory.components.SelectedCategoryTopBar

@Composable
fun SelectedCategory(
    navController: NavController = rememberNavController(),
    selectedCategoryViewModel: SelectedCategoryViewModel,
    numeMaterie: String = "",
) {
    selectedCategoryViewModel.getProfesoriList(numeMaterie)

//    TODO O implementare mai buna a refresh-ului cand alegi alta categorie

    Scaffold(
        topBar = { SelectedCategoryTopBar(
            navController = navController,
            title = numeMaterie
        ) }
    ) {
        LazyColumn {
            items(selectedCategoryViewModel.profesoriList.size) {
                Text(selectedCategoryViewModel.profesoriList[it].nume)
            }
        }
    }
}