package com.example.meditatii_gaseste_tiprofesorul

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.example.meditatii_gaseste_tiprofesorul.common.Screens
import com.example.meditatii_gaseste_tiprofesorul.domain.model.Professor
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.Categories
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.InspectProfessor.InspectProfessor
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.Login
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.Register
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.categories.CategoriesViewModel
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.selectedCategory.SelectedCategory
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.selectedCategory.SelectedCategoryViewModel
import com.google.firebase.auth.FirebaseAuth
import com.squareup.moshi.Moshi

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun Navigation(
    categoriesViewModel: CategoriesViewModel,
    selectedCategoryViewModel: SelectedCategoryViewModel,
    auth: FirebaseAuth,
    moshi: Moshi
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = if (auth.currentUser == null) Screens.Register.route else Screens.Categories.route
    ) {
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
                numeMaterie = backStackEntry.arguments?.getString("numeMaterie")!!,
                moshi = moshi
            )
        }
        composable(
            Screens.InspectProfessor.route,
            arguments = listOf(
                navArgument("professor") { type = NavType.StringType }
            )
        ) { backStackEntry ->

            val professorJson = backStackEntry.arguments?.getString("professor")
            val jsonAdapter = moshi.adapter(Professor::class.java).lenient()
            val professorObject = jsonAdapter.fromJson(professorJson)

            InspectProfessor(
                professor = professorObject!!,
                navController = navController,
            )
        }

        composable(Screens.Login.route) {
            Login(navController)
        }
        composable(Screens.Register.route) {
            Register(navController)
        }
    }
}