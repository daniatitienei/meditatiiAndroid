package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.addAnnouncement

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700
import com.example.meditatii_gaseste_tiprofesorul.common.FieldType
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.addAnnouncement.components.AddAnnouncementTopBar
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.register.components.InputField
import com.example.meditatii_gaseste_tiprofesorul.presentation.theme.MeditatiiTheme
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun AddAnnouncement(
    navController: NavController = rememberNavController(),
//    addAnnouncementViewModel: AddAnnouncementViewModel
) {
    var description by remember {
        mutableStateOf("")
    }

    var price by remember {
        mutableStateOf("")
    }

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = { AddAnnouncementTopBar(navController) },
    ) {
        Column {

            Column(
                modifier = Modifier.fillMaxHeight()
            ) {

                InputField(
                    value = description,
                    onValueChange = { description = it },
                    placeholder = "Descriere",
                    type = FieldType.DESCRIPTION,
                    error = "",
                )

                InputField(
                    value = price,
                    onValueChange = { price = it },
                    placeholder = "Pret",
                    type = FieldType.PRICE,
                    error = "",
                )

                TextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Selectati o materie") },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.background,
                        focusedIndicatorColor = Purple700,
                        cursorColor = Purple700,
                    ),
                    enabled = false,
                    modifier = Modifier.clickable {
                        scope.launch {
                            bottomSheetScaffoldState.bottomSheetState.expand()
                        }
                    }
                )

//                Button(onClick = {
//                    scope.launch {
//                        bottomSheetScaffoldState.bottomSheetState.expand()
//                    }
//                }) {
//                    Text("Toggle sheet", color = Purple700)
//                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
@Preview(showBackground = true)
fun PreviewAddAnnouncement() {
    MeditatiiTheme {
        AddAnnouncement()
    }
}