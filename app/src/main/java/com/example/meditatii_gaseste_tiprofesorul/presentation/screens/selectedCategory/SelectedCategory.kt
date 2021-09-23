package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.selectedCategory

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.selectedCategory.components.ProfessorListTile
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.selectedCategory.components.SelectedCategoryTopBar

@ExperimentalMaterialApi
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
//                Text(selectedCategoryViewModel.profesoriList[it].nume)
                ProfessorListTile(selectedCategoryViewModel.profesoriList[it])

                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}