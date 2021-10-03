package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.selectedCategory

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
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

@ExperimentalMaterialApi
@Composable
fun SelectedCategory(
    navController: NavController = rememberNavController(),
    numeMaterie: String = "",
    moshi: Moshi,
    selectedCategoryViewModel: SelectedCategoryViewModel = hiltViewModel(),
    accountViewModel: AccountViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()

    LaunchedEffect(scope) {
        selectedCategoryViewModel.getProfesoriList(numeMaterie)
    }

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
            if (!accountViewModel.accountDetails.value.isStudent!!)
                AddAnnouncementButton(navController = navController)
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
    ) {
        LazyColumn {
            items(selectedCategoryViewModel.profesoriList.size) {
                ProfessorListTile(
                    profesor = selectedCategoryViewModel.profesoriList[it],
                    onClick = {
                        selectedCategoryViewModel.profesoriList[it].imgUrl = URLEncoder.encode(selectedCategoryViewModel.profesoriList[it].imgUrl, StandardCharsets.UTF_8.toString())
                        selectedCategoryViewModel.profesoriList[it].descriere = reverseSlash(selectedCategoryViewModel.profesoriList[it].descriere)
                        val jsonAdapter = moshi.adapter(Professor::class.java)
                        val professorJson = jsonAdapter.toJson(selectedCategoryViewModel.profesoriList[it])

                        navController.navigate(
                            Screens.InspectProfessor.route.replace("{professor}", professorJson)
                        )
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

fun reverseSlash(text: String): String = text.replace("/", "\\")