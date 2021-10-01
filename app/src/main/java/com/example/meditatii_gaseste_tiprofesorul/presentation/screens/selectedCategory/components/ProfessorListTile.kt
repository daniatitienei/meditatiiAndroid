package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.selectedCategory.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
import com.example.meditatii_gaseste_tiprofesorul.data.model.Professor

@ExperimentalMaterialApi
@Composable
fun ProfessorListTile(profesor: Professor, onClick: () -> Unit) {

    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .componentRegistry {
            add(SvgDecoder(LocalContext.current))
        }
        .build()

    Card(
        elevation = 2.dp,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 20.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                modifier = Modifier.weight(5f)
            ) {
                Box(
                    modifier = Modifier.size(50.dp).clip(CircleShape)
                ) {
                    CompositionLocalProvider(LocalImageLoader provides imageLoader) {
                        val painter = rememberImagePainter(
                            data = profesor.imgUrl,
                            builder = {
                                crossfade(true)
                            }
                        )

                        Image(
                            painter = painter,
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                Spacer(modifier = Modifier.width(15.dp))

                Column {
                    Text(
                        text = "${profesor.nume} ${profesor.prenume}",
                        style = MaterialTheme.typography.body2,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(profesor.judet)
                }
            }
            Row(
                modifier = Modifier.weight(1.5f),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${profesor.pret} LEI",
                    style = MaterialTheme.typography.body2,
                )
            }
        }
    }
}
