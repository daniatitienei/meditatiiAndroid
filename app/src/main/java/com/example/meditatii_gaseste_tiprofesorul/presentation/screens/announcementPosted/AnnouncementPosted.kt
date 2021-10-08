package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.announcementPosted

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700
import com.example.meditatii_gaseste_tiprofesorul.common.Constants
import com.example.meditatii_gaseste_tiprofesorul.common.Screens
import kotlinx.coroutines.*

@Composable
fun AnnouncementPosted(navController: NavController) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.Url(Constants.ANNOUNCEMENT_POSTED_LOTTIE_ANIMATION_URL))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    GlobalScope.launch {
        delay(3000)

        withContext(Dispatchers.Main) {
            navController.navigate(Screens.Categories.route) { launchSingleTop = true }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(composition = composition, progress = progress)
        Text(text = "Anuntul a fost publicat", color = Purple700, style = MaterialTheme.typography.h3)
    }

}