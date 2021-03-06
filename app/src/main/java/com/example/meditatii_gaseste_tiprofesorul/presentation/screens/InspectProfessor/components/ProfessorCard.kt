package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.InspectProfessor.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import com.example.meditatii_gaseste_tiprofesorul.domain.model.Professor

@Composable
fun ProfessorCard(
    profesor: Professor,
    modifier: Modifier,
    svgLoader: ImageLoader
) {
    BoxWithConstraints (
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
               modifier = Modifier.weight(1f)
            ) {
                CompositionLocalProvider(LocalImageLoader provides svgLoader) {
                    val painter = rememberImagePainter(
                        data = profesor.imgUrl,
                        builder = { crossfade(true) }
                    )

                    Image(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier.size(110.dp).clip(CircleShape),
                        contentScale = ContentScale.Crop,
                    )
                }

            }

            Column(
                modifier = Modifier.weight(1.5f),
            ) {
                Text(
                    text = "${profesor.nume} ${profesor.prenume}",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Text("Profesor de ${profesor.materie}", color = Color.White)
            }
        }
    }
}
