package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.createProfessorProfile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700
import com.example.meditatii_gaseste_tiprofesorul.common.FieldType
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.createProfessorProfile.components.SheetContent
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.register.components.InputField
import com.example.meditatii_gaseste_tiprofesorul.presentation.theme.MeditatiiTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun CreateProfessorProfile() {

    var lastName by remember {
        mutableStateOf("")
    }

    var firstName by remember {
        mutableStateOf("")
    }

    var phoneNumber by remember {
        mutableStateOf("")
    }

    var county by remember {
        mutableStateOf("")
    }

    var city by remember {
        mutableStateOf("")
    }

    val coroutineScope = rememberCoroutineScope()

    val citySheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val countySheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    val sheetShape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.White,
            darkIcons = true
        )
    }

    ModalBottomSheetLayout(
        sheetState = citySheetState,
        sheetContent = {
            SheetContent(
                onClick = { city = it },
                sheetState = citySheetState,
                coroutineScope = coroutineScope,
                placeholder = "Selectati un oras",
                isCitySheetContent = true
            )
        },
        sheetShape = sheetShape,
    ) {
        ModalBottomSheetLayout(
            sheetState = countySheetState,
            sheetContent = {
                SheetContent(
                    onClick = { county = it },
                    sheetState = countySheetState,
                    coroutineScope = coroutineScope,
                    placeholder = "Selectati un judet",
                    isCitySheetContent = false
                )
            },
            sheetShape = sheetShape,
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = "Creeaza-ti un profil de profesor") },
                        actions = { IconButton(onClick = { /*TODO*/ }) {
                            Icon(Icons.Rounded.ExitToApp, contentDescription = null, tint = Purple700)
                        } }
                    )
                }
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight().padding(vertical = 10.dp, horizontal = 20.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
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

                        TextField(
                            value = city,
                            onValueChange = {},
                            enabled = false,
                            placeholder = { Text("Selectati un oras") },
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.Transparent,
                                focusedIndicatorColor = Purple700,
                            ),
                            modifier = Modifier.clickable {
                                coroutineScope.launch {
                                    if (citySheetState.isVisible)
                                        citySheetState.hide()
                                    else
                                        citySheetState.show()
                                }
                            }
                        )

                        TextField(
                            value = county,
                            onValueChange = {},
                            enabled = false,
                            placeholder = { Text("Selectati un judet") },
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.Transparent,
                                focusedIndicatorColor = Purple700,
                            ),
                            modifier = Modifier.clickable {
                                coroutineScope.launch {
                                    if (countySheetState.isVisible)
                                        countySheetState.hide()
                                    else
                                        countySheetState.show()
                                }
                            }
                        )
                    }

                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Purple700
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        shape = CircleShape,
                        enabled = !(county.isBlank() || firstName.trim().isBlank() || lastName.trim().isBlank() || city.isBlank() || phoneNumber.isBlank())
                    ) {
                        Text(text = "Creeaza profilul")
                    }
                }
            }
        }
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