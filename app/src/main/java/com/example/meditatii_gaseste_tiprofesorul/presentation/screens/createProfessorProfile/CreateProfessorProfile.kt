package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.createProfessorProfile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.meditatii_gaseste_tiprofesorul.common.FieldType
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.createProfessorProfile.components.CityPicker
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.register.components.InputField
import com.example.meditatii_gaseste_tiprofesorul.presentation.theme.MeditatiiTheme

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun CreateProfessorProfile(

) {

    var lastName by remember {
        mutableStateOf("")
    }

    var firstName by remember {
        mutableStateOf("")
    }

    var phoneNumber by remember {
        mutableStateOf("")
    }

    var city by remember {
        mutableStateOf("")
    }

    var county by remember {
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            InputField(
                value = lastName,
                onValueChange = { lastName = it},
                placeholder = "Nume",
                type = FieldType.BASIC_TEXT
            )

            InputField(
                value = firstName,
                onValueChange = { firstName = it },
                placeholder = "Prenume",
                type = FieldType.BASIC_TEXT
            )

            InputField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                placeholder = "Numar de telefon",
                leadingIcon = { Text(text = "+ 40") },
                type = FieldType.PHONE_NUMBER
            )
        }
        CityPicker()
    }
}

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Preview (showBackground = true)
@Composable
fun PreviewProfessorProfile() {
    MeditatiiTheme {
        CreateProfessorProfile()
    }
}