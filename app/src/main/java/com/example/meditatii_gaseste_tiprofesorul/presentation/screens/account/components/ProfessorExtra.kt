package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.categories.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.account.AccountViewModel

@Composable
fun ProfessorExtra(
    accountViewModel: AccountViewModel,
    svgLoader: ImageLoader
) {
    val profil = accountViewModel.accountDetails.value.profil

    CompositionLocalProvider(LocalImageLoader provides svgLoader) {
        val painter = rememberImagePainter(
            data = profil?.get("imgUrl"),
            builder = {
                crossfade(true)
            }
        )

        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(150.dp)
                .border(color = Purple700, shape = CircleShape, width = 2.dp)
        )
    }

    Spacer(modifier = Modifier.height(15.dp))

    Text(text = "${profil?.get("nume")!!} ${profil.get("prenume")}", style = MaterialTheme.typography.h4)

    Spacer(modifier = Modifier.height(3.dp))
}
