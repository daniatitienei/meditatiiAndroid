package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.createProfessorProfile.components

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.*

@Composable
fun ImagePicker() {
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val bitmap = remember {
        mutableStateOf<Bitmap?>(null)
    }



}