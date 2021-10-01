package com.example.meditatii_gaseste_tiprofesorul

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SelectRole(
    navController: NavController,
    svgLoader: ImageLoader,
) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.White,
            darkIcons = true
        )
    }

    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
    ) {
        CompositionLocalProvider(LocalImageLoader provides svgLoader) {
            val imageLink = "https://firebasestorage.googleapis.com/v0/b/gaseste-ti-profesorul.appspot.com/o/svg%2FDesignerFlatline.svg?alt=media&token=934d45ed-e7c1-41ad-839d-c7c2c063aec0"
            val painter = rememberImagePainter(
                data = imageLink,
                builder = {
                    crossfade(true)
                }
            )

            Image(
                painter = painter,
                modifier = Modifier.padding(horizontal = 10.dp),
                contentDescription = null,
            )
        }

        Column(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Alege-ti rolul",
                color = Purple700,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "Selectati categoria din care faceti parte.",
                color = Purple700,
            )
        }

        Column(
            modifier = Modifier
                .padding(all = 20.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            RoleButton(text = "Student", onClick = {})

            Spacer(modifier = Modifier.height(10.dp))

            RoleButton(text = "Profesor", onClick = {})
        }
    }
}

@Composable
fun RoleButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Purple700
        ),
        shape = CircleShape
    ) {
        Text(text = text)
    }
}