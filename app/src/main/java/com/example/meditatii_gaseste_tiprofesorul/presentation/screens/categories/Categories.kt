package com.example.meditatii_gaseste_tiprofesorul.presentation.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.example.meditatii_gaseste_tiprofesorul.common.Screens
import com.example.meditatii_gaseste_tiprofesorul.common.components.AddAnnouncementButton
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.account.AccountViewModel
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.categories.CategoriesViewModel
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.categories.components.CategoriesAppBar
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.categories.components.CategoryTileList
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.auth.FirebaseAuth

@ExperimentalCoilApi
@Composable
fun Categories(
    navController: NavController,
    auth: FirebaseAuth,
    categoriesViewModel: CategoriesViewModel = hiltViewModel(),
    accountViewModel: AccountViewModel = hiltViewModel()
) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.White,
            darkIcons = true
        )
    }

    Scaffold(
        topBar = { CategoriesAppBar(navController, auth) },
        floatingActionButton = {
            if (!accountViewModel.accountDetails.value.isStudent!!)
                AddAnnouncementButton(navController = navController)
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,

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