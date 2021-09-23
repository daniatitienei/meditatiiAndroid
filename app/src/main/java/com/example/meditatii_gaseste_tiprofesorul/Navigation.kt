package com.example.meditatii_gaseste_tiprofesorul

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.example.meditatii_gaseste_tiprofesorul.common.Screens
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.Categories
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.categories.CategoriesViewModel
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.selectedCategory.SelectedCategory
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.selectedCategory.SelectedCategoryViewModel

@ExperimentalCoilApi
@Composable
fun Navigation(
    categoriesViewModel: CategoriesViewModel,
    selectedCategoryViewModel: SelectedCategoryViewModel,
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.Categories.route) {
        composable(Screens.Categories.route) {
            Categories(navController, categoriesViewModel)
        }
        composable(
            Screens.SelectedCategory.route,
            arguments = listOf(
                navArgument("numeMaterie") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            SelectedCategory(
                navController = navController,
                selectedCategoryViewModel = selectedCategoryViewModel,
                numeMaterie = backStackEntry.arguments?.getString("numeMaterie")!!
            )
        }
    }
}