package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.selectedCategory

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.airbnb.lottie.compose.*
import com.example.meditatii_gaseste_tiprofesorul.common.Constants
import com.example.meditatii_gaseste_tiprofesorul.common.Screens
import com.example.meditatii_gaseste_tiprofesorul.common.components.AddAnnouncementButton
import com.example.meditatii_gaseste_tiprofesorul.domain.model.Professor
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.account.AccountViewModel
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.selectedCategory.components.ProfessorListTile
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.selectedCategory.components.SelectedCategoryTopBar
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.squareup.moshi.Moshi
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@ExperimentalFoundationApi
@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun SelectedCategory(
    navController: NavController = rememberNavController(),
    numeMaterie: String = "",
    moshi: Moshi,
    selectedCategoryViewModel: SelectedCategoryViewModel,
    accountViewModel: AccountViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()

    selectedCategoryViewModel.getProfesoriList(numeMaterie)

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.White,
            darkIcons = true
        )
    }

    Scaffold(
        topBar = {
            SelectedCategoryTopBar(
                navController = navController,
                title = numeMaterie
            )
        },
        floatingActionButton = {
            if (accountViewModel.accountDetails.value.isStudent != null)
                if (!accountViewModel.accountDetails.value.isStudent!!)
                    AddAnnouncementButton(navController = navController)
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
    ) {
        if (!selectedCategoryViewModel.profesoriList.value?.isEmpty()!!)
            LazyColumn {
                items(selectedCategoryViewModel.profesoriList.value!!.size) {
                    ProfessorListTile(
                        professor = selectedCategoryViewModel.profesoriList.value!![it],
                        onClick = {
                            selectedCategoryViewModel.profesoriList.value!![it].imgUrl = URLEncoder.encode(
                                selectedCategoryViewModel.profesoriList.value!![it].imgUrl, StandardCharsets.UTF_8.toString())
                            selectedCategoryViewModel.profesoriList.value!![it].descriere = reverseSlash(
                                selectedCategoryViewModel.profesoriList.value!![it].descriere)
                            val jsonAdapter = moshi.adapter(Professor::class.java)
                            val professorJson = jsonAdapter.toJson(selectedCategoryViewModel.profesoriList.value!![it])

                            navController.navigate(
                                Screens.InspectProfessor.route.replace("{professor}", professorJson)
                            ) { launchSingleTop = true }
                        }
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        else {
            val composition by rememberLottieComposition(spec = LottieCompositionSpec.Url(Constants.EMTPY_DESK_LOTTIE_ANIMATION_URL))
            val progress by animateLottieCompositionAsState(
                composition = composition,
                iterations = LottieConstants.IterateForever
            )

            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                LottieAnimation(composition = composition, progress = progress)
            }
        }
    }
}

fun reverseSlash(text: String): String = text.replace("/", "\\")