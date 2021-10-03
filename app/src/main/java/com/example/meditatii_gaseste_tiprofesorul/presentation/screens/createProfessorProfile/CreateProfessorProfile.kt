package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.createProfessorProfile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700
import com.example.meditatii_gaseste_tiprofesorul.common.FieldType
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.createProfessorProfile.components.CityPicker
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.register.components.InputField
import com.example.meditatii_gaseste_tiprofesorul.presentation.theme.MeditatiiTheme
import kotlinx.coroutines.launch

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

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )

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

            Button(onClick = { scope.launch {
                if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                    bottomSheetScaffoldState.bottomSheetState.expand()
                } else {
                    bottomSheetScaffoldState.bottomSheetState.collapse()
                }
            } }) {
                Text(text = "Launch", color = Purple700)
            }
        }

        CityPicker(state = bottomSheetScaffoldState)
//        TODO Orasul, judetul
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