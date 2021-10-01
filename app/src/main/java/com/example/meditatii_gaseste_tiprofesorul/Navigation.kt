package com.example.meditatii_gaseste_tiprofesorul

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import com.example.meditatii_gaseste_tiprofesorul.common.Screens
import com.example.meditatii_gaseste_tiprofesorul.data.model.Professor
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.Account
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.Categories
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.InspectProfessor.InspectProfessor
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.Login
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.Register
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.account.AccountViewModel
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.addAnnouncement.AddAnnouncement
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.addAnnouncement.AddAnnouncementViewModel
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.categories.CategoriesViewModel
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.login.LoginViewModel
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.register.RegisterViewModel
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
    registerViewModel: RegisterViewModel,
    loginViewModel: LoginViewModel,
    accountViewModel: AccountViewModel,
    addAnnouncementViewModel: AddAnnouncementViewModel,
    auth: FirebaseAuth,
    moshi: Moshi,
    svgLoader: ImageLoader
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = if (auth.currentUser == null) Screens.Register.route else Screens.Categories.route
        // TODO Conditie pentru profesori daca nu si-au facut profil sa-i redirectioneze pe ala
        // TODO Cand dai pe register sa te puna sa selectezi rolul
    ) {
        composable(Screens.Categories.route) {
            Categories(navController, categoriesViewModel, auth)
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
                svgLoader = svgLoader
            )
        }

        composable(Screens.Login.route) {
            Login(navController, loginViewModel, svgLoader)
        }
        composable(Screens.Register.route) {
            Register(navController, registerViewModel, svgLoader)
        }
        composable(Screens.Account.route) {
            Account(navController, auth, accountViewModel, svgLoader)
        }
        composable(Screens.AddAnnouncement.route) {
            AddAnnouncement(navController, addAnnouncementViewModel, svgLoader)
        }
        composable(Screens.SelectRole.route) {
            SelectRole(navController, svgLoader)
        }
    }
}