package com.example.meditatii_gaseste_tiprofesorul

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700
import com.example.meditatii_gaseste_tiprofesorul.theme.MeditatiiTheme
import com.example.meditatii_gaseste_tiprofesorul.ui.theme.MeditatiiGasestetiProfesorulTheme

@Composable
fun SelectRole() {
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
    ) {
        val imageLoader = ImageLoader.Builder(LocalContext.current)
            .componentRegistry {
                add(SvgDecoder(LocalContext.current))
            }
            .build()

        CompositionLocalProvider(LocalImageLoader provides imageLoader) {
            val imageLink = "https://firebasestorage.googleapis.com/v0/b/gaseste-ti-profesorul.appspot.com/o/svg%2FDesignerFlatline.svg?alt=media&token=934d45ed-e7c1-41ad-839d-c7c2c063aec0"
            val painter = rememberImagePainter(data = imageLink)

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
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(),
                shape = CircleShape
            ) {
                Text(text = "Student",)
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(),
                shape = CircleShape
            ) {
                Text(text = "Profesor",)
            }
        }
    }
}