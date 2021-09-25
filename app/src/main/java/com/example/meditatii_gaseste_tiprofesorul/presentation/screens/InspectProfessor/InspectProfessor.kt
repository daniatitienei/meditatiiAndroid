package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.InspectProfessor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.meditatii_gaseste_tiprofesorul.common.Constants
import com.example.meditatii_gaseste_tiprofesorul.domain.model.Profesor
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.InspectProfessor.components.InspectProfessorTopBar
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.InspectProfessor.components.ProfessorCard
import com.example.meditatii_gaseste_tiprofesorul.presentation.theme.MeditatiiTheme

@Composable
fun InspectProfessor(profesor: Profesor) {
    Scaffold(
        topBar = { InspectProfessorTopBar() }
    ) {
        Column(
            modifier = Modifier.fillMaxHeight()
        ) {
            ProfessorCard(profesor)
        }
    }
}

@Composable
@Preview (showBackground = true)
fun PreviewProfessor() {
    MeditatiiTheme {
        InspectProfessor(Constants.prof)
    }
}