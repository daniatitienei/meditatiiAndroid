package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.createProfessorProfile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.createProfessorProfile.CitiesViewModel
import com.example.meditatii_gaseste_tiprofesorul.presentation.theme.MeditatiiTheme
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun CityPicker(
    citiesViewModel: CitiesViewModel = hiltViewModel(),
    value: String,
    placeholder: String,
    onClick: (String) -> Unit,
    bottomSheetScaffoldState: BottomSheetScaffoldState
) {
//    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
//        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
//    )

    val keyboardController = LocalSoftwareKeyboardController.current

    val coroutineScope = rememberCoroutineScope()

    var searchValue by remember {
        mutableStateOf("")
    }

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetShape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
//        TODO background color
        sheetContent = {
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
            ) {

                Column(
                    modifier = Modifier.padding(top = 20.dp)
                ) {
                    SearchTextField(
                        value = searchValue,
                        onValueChange = { searchValue = it},
                        placeholder = "Cautati un oras",
                        keyboardController = keyboardController!!
                    )
                    if (!citiesViewModel.citiesState.value.isLoading) {
                        val cities = citiesViewModel.citiesState.value.cities.filter {
                              it.nume.contains(searchValue, ignoreCase = true)
                        }
                        LazyColumn() {
                            items(cities.size) {
                                Box(
                                    modifier = Modifier.fillMaxWidth()
                                        .background(Color.Magenta)
                                        .clickable {
                                            onClick(cities[it].nume)
                                            searchValue = ""
                                        }
                                        .padding(vertical = 8.dp)
                                ) {
                                    Text(
                                        text = cities[it].nume,
                                        style = MaterialTheme.typography.body2
                                    )
                                }

                                Spacer(modifier = Modifier.height(5.dp))
                            }
                        }
                    }
                    else
                        Column(
                            modifier = Modifier.fillMaxWidth().fillMaxHeight(0.8f),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator(color = Purple700)
                        }
                }
            }
        }, sheetPeekHeight = 0.dp
    ) {

        TextField(
            value = value,
            onValueChange = {},
            placeholder = { Text(placeholder) },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Purple700,
            ),
        )
    }
}

@ExperimentalComposeUiApi
@Composable
fun SearchTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardController: SoftwareKeyboardController
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = placeholder) },
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = Purple700,
            backgroundColor = Color.LightGray,
            leadingIconColor = Purple700,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clip(CircleShape),
        leadingIcon = {
            Icon(Icons.Rounded.Search, contentDescription = null)
//                            Todo sa apara un x ca sa stearga tot
        },
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController.hide()
            }
        ),
    )
    Spacer(modifier = Modifier.height(20.dp))
}

@ExperimentalMaterialApi
@Preview (showBackground = true)
@Composable
fun PreviewCityPicker() {
    MeditatiiTheme {
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 20.dp)
        ) {
            LazyColumn() {
                item {
                    TextField(
                        value = "",
                        onValueChange = {},
                        placeholder = { Text(text = "Cautati un oras") },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.LightGray,
                            leadingIconColor = Purple700,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(CircleShape),
                        leadingIcon = {
                            Icon(Icons.Rounded.Search, contentDescription = null)
//                            Todo sa apara un x ca sa stearga tot
                        }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
                items(20) {
                    Text(text = it.toString())
                }
            }
        }
    }
}