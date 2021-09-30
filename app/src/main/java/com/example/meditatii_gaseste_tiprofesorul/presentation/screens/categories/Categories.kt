package com.example.meditatii_gaseste_tiprofesorul.presentation.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700
import com.example.meditatii_gaseste_tiprofesorul.common.Screens
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.categories.CategoriesViewModel
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.categories.components.CategoriesAppBar
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.categories.components.CategoryTileList
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.auth.FirebaseAuth

@ExperimentalCoilApi
@Composable
fun Categories(
    navController: NavController,
    categoriesViewModel: CategoriesViewModel,
    auth: FirebaseAuth,
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
        floatingActionButton = { FloatingActionButton(
            onClick = {},
            shape = CircleShape,
            content = {
                Icon(Icons.Rounded.Add, contentDescription = null, tint = Color.White)
            },
            backgroundColor = Purple700,
        ) },
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