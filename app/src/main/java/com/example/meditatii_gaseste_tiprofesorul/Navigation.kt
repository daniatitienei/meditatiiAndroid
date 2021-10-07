package com.example.meditatii_gaseste_tiprofesorul

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import com.example.meditatii_gaseste_tiprofesorul.common.Screens
import com.example.meditatii_gaseste_tiprofesorul.domain.model.Professor
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.Account
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.Categories
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.InspectProfessor.InspectProfessor
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.Login
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.Register
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.account.AccountViewModel
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.addAnnouncement.AddAnnouncement
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.createProfessorProfile.CreateProfessorProfile
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.selectedCategory.SelectedCategory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.moshi.Moshi

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun Navigation(
    auth: FirebaseAuth,
    moshi: Moshi,
    firestore: FirebaseFirestore,
    svgLoader: ImageLoader,
    accountViewModel: AccountViewModel = hiltViewModel()
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination(accountViewModel = accountViewModel, auth = auth)
        // TODO Conditie pentru profesori daca nu si-au facut profil sa-i redirectioneze pe ala
        // TODO Cand dai pe register sa te puna sa selectezi rolul
    ) {
        composable(Screens.Login.route) {
            Login(
                navController = navController,
                svgLoader = svgLoader,
                auth = auth
            )
        }

        composable(Screens.Register.route) {
            Register(
                navController = navController,
                svgLoader = svgLoader
            )
        }

        composable(Screens.CreateProfessorProfile.route) {
            CreateProfessorProfile(
                navController = navController,
                auth = auth
            )
        }

        composable(Screens.Categories.route) {
            Categories(
                navController = navController,
                auth = auth,
            )
        }
        composable(
            Screens.SelectedCategory.route,
            arguments = listOf(
                navArgument("numeMaterie") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            SelectedCategory(
                navController = navController,
                numeMaterie = backStackEntry.arguments?.getString("numeMaterie")!!,
                moshi = moshi,
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

        composable(Screens.Account.route) {
            Account(
                navController = navController,
                auth = auth,
                svgLoader = svgLoader
            )
        }
        composable(Screens.AddAnnouncement.route) {
            AddAnnouncement(
                navController = navController,
                svgLoader = svgLoader,
            )
        }
        composable(Screens.SelectRole.route) {
            SelectRole(
                navController = navController,
                firestore = firestore,
                auth = auth,
                svgLoader = svgLoader
            )
        }
    }
}

fun startDestination(accountViewModel: AccountViewModel, auth: FirebaseAuth): String {
    return if (auth.currentUser == null)
        Screens.Register.route
    else if (auth.currentUser != null && accountViewModel.accountDetails.value.isStudent == null)
        Screens.SelectRole.route
    else if (auth.currentUser != null && !accountViewModel.accountDetails.value.isStudent!! && accountViewModel.accountDetails.value.profil.isNullOrEmpty())
        Screens.CreateProfessorProfile.route
    else
        Screens.Categories.route
}