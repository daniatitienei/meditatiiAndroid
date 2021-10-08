package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.favorites

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.airbnb.lottie.compose.*
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700
import com.example.meditatii_gaseste_tiprofesorul.common.Constants
import com.example.meditatii_gaseste_tiprofesorul.common.Screens
import com.example.meditatii_gaseste_tiprofesorul.domain.model.Professor
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.selectedCategory.components.ProfessorListTile
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.selectedCategory.reverseSlash
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.squareup.moshi.Moshi
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@ExperimentalFoundationApi
@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun Favorites(
    favoritesViewModel: FavoritesViewModel = hiltViewModel(),
    moshi: Moshi,
    navController: NavController
) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.White,
            darkIcons = true
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Favorite") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Rounded.ArrowBack, contentDescription = null, tint = Purple700)
                    }
                }
            )
        }
    ) {
        if (!favoritesViewModel.favorites.isEmpty())
            LazyColumn() {
                items(favoritesViewModel.favorites.size) {
                    ProfessorListTile(
                        professor = favoritesViewModel.favorites[it],
                        onClick = {
                            favoritesViewModel.favorites[it].imgUrl = URLEncoder.encode(favoritesViewModel.favorites[it].imgUrl, StandardCharsets.UTF_8.toString())
                            favoritesViewModel.favorites[it].descriere = reverseSlash(favoritesViewModel.favorites[it].descriere)
                            val jsonAdapter = moshi.adapter(Professor::class.java)
                            val professorJson = jsonAdapter.toJson(favoritesViewModel.favorites[it])

                            navController.navigate(
                                Screens.InspectProfessor.route.replace("{professor}", professorJson)
                            ) { launchSingleTop = true }
                        }
                    )
                }
            }
        else {
            val composition by rememberLottieComposition(spec = LottieCompositionSpec.Url(Constants.FAVORITE_EMPTY_LOTTIE_ANIMATION_URL))
            val progress by animateLottieCompositionAsState(
                composition = composition,
                iterations = LottieConstants.IterateForever
            )

            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                LottieAnimation(composition = composition, progress = progress)
            }
        }
    }

}