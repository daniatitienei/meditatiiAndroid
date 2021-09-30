package com.example.meditatii_gaseste_tiprofesorul.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700
import com.example.meditatii_gaseste_tiprofesorul.common.FieldType
import com.example.meditatii_gaseste_tiprofesorul.common.Screens
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.register.RegisterViewModel
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.register.components.InputField
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.register.components.LoginWithGoogleButton
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@ExperimentalComposeUiApi
@Composable
fun Register(
    navController: NavController,
    registerViewModel: RegisterViewModel,
    svgLoader: ImageLoader
) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.White,
            darkIcons = true
        )
    }

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val isValid = android.util.Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches() && password.trim().isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Inregistrare", style = MaterialTheme.typography.h3)
        CompositionLocalProvider(LocalImageLoader provides svgLoader) {
            val imageLink = "https://firebasestorage.googleapis.com/v0/b/gaseste-ti-profesorul.appspot.com/o/svg%2Fusers_profile.png?alt=media&token=afa9927a-83d4-4255-b6bb-0e8f7fe3ea8d"
            val painter = rememberImagePainter(
                data = imageLink,
                builder = {
                    crossfade(true)
                }
            )

            Image(
                painter = painter,
                modifier = Modifier.size(200.dp),
                contentDescription = null,
            )
        }


        Column(
            modifier = Modifier.padding(top = 20.dp),
            horizontalAlignment = Alignment.End,
        ) {
            InputField(
                value = email,
                onValueChange = { email = it },
                placeholder = "Email",
                type = FieldType.EMAIL,
                error = registerViewModel.emailError.value,
            )

            Spacer(modifier = Modifier.height(10.dp))

            InputField(
                value = password,
                onValueChange = { password = it },
                placeholder = "Parola",
                type = FieldType.PASSWORD,
                error = registerViewModel.passwordError.value,
            )
            
            TextButton(
                onClick = {
                    navController.navigate(Screens.Login.route) {
                        launchSingleTop = true
                    }
                },
            ) {
                Text(
                    text = "Am cont",
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold,
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = {
                registerViewModel.registerWithEmailAndPassword(email.trim(), password.trim())
            },
            modifier = Modifier.fillMaxWidth(),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Purple700
            ),
            enabled = isValid
        ) {
            Text(text = "Creeaza cont")
        }

        Spacer(modifier = Modifier.height(5.dp))

        Text("SAU")

        Spacer(modifier = Modifier.height(5.dp))

        LoginWithGoogleButton()
    }
}