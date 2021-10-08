package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.myAnnouncements

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
fun MyAnnouncements(
    myAnnouncementsViewModel: MyAnnouncementsViewModel = hiltViewModel(),
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

    var openDialog by remember {
        mutableStateOf(false)
    }

    var professor by remember {
        mutableStateOf(Professor())
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Anunturile mele") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Rounded.ArrowBack, contentDescription = null, tint = Purple700)
                    }
                }
            )
        }
    ) {

        if (openDialog)
            AlertDialog(
                onDismissRequest = { openDialog = false },
                title = { Text(text = "Doriti sa stergeti anuntul?", style = MaterialTheme.typography.body2) },
                confirmButton = {
                    Button(
                        onClick = {
                            myAnnouncementsViewModel.removeMyAnnouncement(professor = professor)
                            openDialog = false
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Purple700
                        )
                    ) {
                        Text(text = "Da")
                    }
                },
                dismissButton = {
                    Button(onClick = {
                        openDialog = false
                    }, colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Red
                    )) {
                        Text(text = "Nu")
                    }
                }
            )

        if (!myAnnouncementsViewModel.anunturi.isEmpty())
            LazyColumn() {
                items(myAnnouncementsViewModel.anunturi.size) {
                    ProfessorListTile(
                        professor = myAnnouncementsViewModel.anunturi[it],
                        onLongClick = { openDialog = true; professor = myAnnouncementsViewModel.anunturi[it] },
                        onClick = {
                            myAnnouncementsViewModel.anunturi[it].imgUrl = URLEncoder.encode(myAnnouncementsViewModel.anunturi[it].imgUrl, StandardCharsets.UTF_8.toString())
                            myAnnouncementsViewModel.anunturi[it].descriere = reverseSlash(myAnnouncementsViewModel.anunturi[it].descriere)
                            val jsonAdapter = moshi.adapter(Professor::class.java)
                            val professorJson = jsonAdapter.toJson(myAnnouncementsViewModel.anunturi[it])

                            navController.navigate(
                                Screens.InspectProfessor.route.replace("{professor}", professorJson)
                            ) { launchSingleTop = true }
                        }
                    )
                }
            }
        else {
            val composition by rememberLottieComposition(spec = LottieCompositionSpec.Url(Constants.SAD_CAT_LOTTIE_ANIMATION_URL))
            val progress by animateLottieCompositionAsState(
                composition = composition,
                iterations = LottieConstants.IterateForever
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LottieAnimation(composition = composition, progress = progress)
            }
        }
    }

}