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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
import com.example.meditatii_gaseste_tiprofesorul.R
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700

@ExperimentalComposeUiApi
@Composable
fun Login() {
    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .componentRegistry {
            add(SvgDecoder(LocalContext.current))
        }
        .build()

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Conectare", style = MaterialTheme.typography.h3)
        CompositionLocalProvider(LocalImageLoader provides imageLoader) {
            val imageLink = "https://firebasestorage.googleapis.com/v0/b/gaseste-ti-profesorul.appspot.com/o/svg%2Fusers_profile.png?alt=media&token=afa9927a-83d4-4255-b6bb-0e8f7fe3ea8d"
            val painter = rememberImagePainter(data = imageLink)

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
                obscureIcon = false
            )

            Spacer(modifier = Modifier.height(10.dp))

            InputField(
                value = password,
                onValueChange = { password = it },
                placeholder = "Parola",
                obscureIcon = true
            )

            TextButton(onClick = { /*TODO*/ }, ) {
                Text(
                    text = "Nu am cont",
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold,
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth(),
            shape = CircleShape
        ) {
            Text(text = "Conectare")
        }

        Spacer(modifier = Modifier.height(5.dp))

        Text("SAU")

        Spacer(modifier = Modifier.height(5.dp))

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth(),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.google),
                contentDescription = "Continua cu Google",
                modifier = Modifier.size(26.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "Continua cu Google",
                color = Purple700,
            )
        }
    }
}