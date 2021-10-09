package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.filters

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.createProfessorProfile.components.SheetContent
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.selectedCategory.SelectedCategoryViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun Filters(
    selectedCategoryViewModel: SelectedCategoryViewModel,
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()

    val citySheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    val sheetShape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.White,
            darkIcons = true
        )
    }

    var city by remember {
        mutableStateOf("")
    }

    var sort by remember {
        mutableStateOf("")
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
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = "Filtre") },
                        navigationIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(Icons.Rounded.ArrowBack, contentDescription = null, tint = Purple700)
                            }
                        }
                    )
                }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(all = 20.dp),

                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(text = "Pret", style = MaterialTheme.typography.body2)

                        TextField(
                            value = sort,
                            onValueChange = {},
                            placeholder = { Text(text = "Crescator") },
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.White,
                            )
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(text = "Oras", style = MaterialTheme.typography.body2)

                        TextField(
                            value = city,
                            onValueChange = {},
                            enabled = false,
                            placeholder = { Text("Oriunde") },
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
                    }

                    Button(
                        onClick = {
                            selectedCategoryViewModel.oras.value = city
                            selectedCategoryViewModel.ordonare.value = sort

                            navController.popBackStack()
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Purple700
                        ),
                        shape = CircleShape,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Salveaza modificarile")
                    }
                }
            }

    }
}
