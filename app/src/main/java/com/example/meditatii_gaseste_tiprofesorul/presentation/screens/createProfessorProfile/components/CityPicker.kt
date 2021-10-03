package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.createProfessorProfile.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.createProfessorProfile.CitiesViewModel
import com.example.meditatii_gaseste_tiprofesorul.presentation.theme.MeditatiiTheme
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun CityPicker(
//    state: BottomSheetScaffoldState,
    citiesViewModel: CitiesViewModel = hiltViewModel()
) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Expanded)
    )
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
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f)
                    .padding(horizontal = 20.dp)
            ) {

                Column(
                    modifier = Modifier.padding(top = 20.dp)
                ) {
                    SearchTextField(
                        value = searchValue,
                        onValueChange = { searchValue = it},
                        placeholder = "Cautati un oras"
                    )
                    LazyColumn() {
                        items(40) {
                            Text(text = it.toString())
                        }
                    }
                }

//                if (!citiesViewModel.citiesState.value.isLoading)
//                    LazyColumn() {
//                        items(citiesViewModel.citiesState.value.cities.size) {
//                            Text(text = citiesViewModel.citiesState.value.cities[it].nume)
//                        }
//                    }
//                else CircularProgressIndicator(color = Purple700)
            }
        }, sheetPeekHeight = 0.dp
    ) {
        Button(onClick = {
            coroutineScope.launch {

                if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                    bottomSheetScaffoldState.bottomSheetState.expand()
                } else {
                    bottomSheetScaffoldState.bottomSheetState.collapse()
                }
            }
        }) {
            Text(text = "Expand/Collapse Bottom Sheet", color = Purple700)
        }
    }
}

@Composable
fun SearchTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = placeholder) },
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