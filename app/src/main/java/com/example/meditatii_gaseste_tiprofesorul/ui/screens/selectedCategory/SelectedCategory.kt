package com.example.meditatii_gaseste_tiprofesorul.ui.screens.selectedCategory

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.meditatii_gaseste_tiprofesorul.common.Screens
import com.example.meditatii_gaseste_tiprofesorul.domain.model.Professor
import com.example.meditatii_gaseste_tiprofesorul.ui.screens.selectedCategory.components.ProfessorListTile
import com.example.meditatii_gaseste_tiprofesorul.ui.screens.selectedCategory.components.SelectedCategoryTopBar
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.squareup.moshi.Moshi
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@ExperimentalMaterialApi
@Composable
fun SelectedCategory(
    navController: NavController = rememberNavController(),
    selectedCategoryViewModel: SelectedCategoryViewModel,
    numeMaterie: String = "",
    moshi: Moshi,
) {
//    TODO O implementare mai buna a refresh-ului cand alegi alta categorie

    selectedCategoryViewModel.getProfesoriList(numeMaterie)

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.White,
            darkIcons = true
        )
    }

    Scaffold(
        topBar = { SelectedCategoryTopBar(
            navController = navController,
            title = numeMaterie
        ) }
    ) {
        LazyColumn {
            items(selectedCategoryViewModel.profesoriList.size) {
                ProfessorListTile(
                    profesor = selectedCategoryViewModel.profesoriList[it],
                    onClick = {
                        selectedCategoryViewModel.profesoriList[it].imgUrl = URLEncoder.encode(selectedCategoryViewModel.profesoriList[it].imgUrl, StandardCharsets.UTF_8.toString())
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