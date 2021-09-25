package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.InspectProfessor.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.meditatii_gaseste_tiprofesorul.R
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700
import com.example.meditatii_gaseste_tiprofesorul.common.Constants
import com.example.meditatii_gaseste_tiprofesorul.domain.model.Profesor
import com.example.meditatii_gaseste_tiprofesorul.presentation.theme.MeditatiiTheme

@Composable
fun ProfessorCard(profesor: Profesor) {
    BoxWithConstraints (
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .defaultMinSize(
                minHeight = 150.dp,
            )
            .clip(RoundedCornerShape(
                bottomEnd = 15.dp,
                bottomStart = 15.dp
            ))
            .background(Purple700)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
               modifier = Modifier.weight(1f)
            ) {
                Image(
                    painter = painterResource(R.drawable.eu),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp).clip(CircleShape),
                    contentScale = ContentScale.Crop,
                )
            }

            Column(
                modifier = Modifier.weight(2f),
            ) {
                Text(
                    text = "${profesor.nume} ${profesor.prenume}",

                    color = Color.White,
                )
                Text("Profesor de ${profesor.materie}", color = Color.White)
            }
        }
    }
}

@Composable
@Preview
fun PreviewProfessorCard() {
    MeditatiiTheme {
        ProfessorCard(Constants.prof)
    }
}