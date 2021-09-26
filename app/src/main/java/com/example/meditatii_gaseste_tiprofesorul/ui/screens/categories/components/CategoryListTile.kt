package com.example.meditatii_gaseste_tiprofesorul.ui.screens.categories.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700
import com.example.meditatii_gaseste_tiprofesorul.model.Materie

@ExperimentalCoilApi
@Composable
fun CategoryTileList(
    onClick: () -> Unit = {},
    materie: Materie = Materie(),
) {

    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .componentRegistry {
            add(SvgDecoder(LocalContext.current))
        }
        .build()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(materie.backgroundColor)
            )
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(
                        color = Color(materie.circleColor)
                    )
                    .padding(10.dp)
            ) {
                CompositionLocalProvider(LocalImageLoader provides imageLoader) {
                    val painter = rememberImagePainter(data = materie.imageUrl)

                    Image(
                        painter = painter,
                        modifier = Modifier.size(30.dp),
                        contentDescription = null,
                    )
                }
            }

            Spacer(modifier = Modifier.width(20.dp))

            Column {
                Text(
                    text = materie.nume,
                    style = MaterialTheme.typography.body2,
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(text = "${materie.anunturi} anunturi")
            }
        }
        IconButton(onClick = { /* TODO */ }) {
            Icon(
                Icons.Rounded.ArrowForward,
                contentDescription = null,
                tint = Purple700
            )
        }
    }
}
