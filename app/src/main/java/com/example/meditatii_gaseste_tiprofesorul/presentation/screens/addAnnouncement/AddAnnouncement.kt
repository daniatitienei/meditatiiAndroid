package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.addAnnouncement

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700
import com.example.meditatii_gaseste_tiprofesorul.common.FieldType
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.addAnnouncement.components.AddAnnouncementTopBar
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.addAnnouncement.components.MateriePicker
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.register.components.InputField
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun AddAnnouncement(
    navController: NavController = rememberNavController(),
    addAnnouncementViewModel: AddAnnouncementViewModel,
    svgLoader: ImageLoader,
) {
    var description by remember {
        mutableStateOf("")
    }

    var price by remember {
        mutableStateOf("")
    }

    var selectedMaterie by remember {
        mutableStateOf("")
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.White,
            darkIcons = true
        )
    }

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )

    val scope = rememberCoroutineScope()
    
    Scaffold(
        topBar = { AddAnnouncementTopBar(navController) },
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(15.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CompositionLocalProvider(LocalImageLoader provides svgLoader) {
                        val imgUrl = "https://firebasestorage.googleapis.com/v0/b/gaseste-ti-profesorul.appspot.com/o/svg%2Fanunt-nou.svg?alt=media&token=e88d5828-a2e4-40bb-8e0d-767b94934fd7"

                        val painter = rememberImagePainter(
                            data = imgUrl,
                            builder = {
                                crossfade(true)
                            }
                        )

                        Image(
                            painter = painter,
                            contentDescription = null,
                            modifier = Modifier
                                .size(220.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(15.dp))

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
                    value = selectedMaterie,
                    onValueChange = {},
                    placeholder = { Text("Selectati o materie") },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.background,
                        focusedIndicatorColor = Purple700,
                        cursorColor = Purple700,
                    ),
                    enabled = false,
                    modifier = Modifier.clickable {
                        expanded = !expanded
                    }
                )

                MateriePicker(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    addAnnouncementViewModel = addAnnouncementViewModel,
                    onSelected = {
                        selectedMaterie = it
                        expanded = !expanded
                    }
                )
            }
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Purple700
                ),
                shape = CircleShape,
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                Text(text = "Adauga un anunt")
            }
        }

    }
}

//@ExperimentalComposeUiApi
//@ExperimentalMaterialApi
//@Composable
//@Preview(showBackground = true)
//fun PreviewAddAnnouncement() {
//    MeditatiiTheme {
//        AddAnnouncement()
//    }
//}