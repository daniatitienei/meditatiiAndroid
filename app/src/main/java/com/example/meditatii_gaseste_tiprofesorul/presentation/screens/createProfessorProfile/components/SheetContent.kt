package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.createProfessorProfile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.createProfessorProfile.CitiesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun SheetContent(
    citiesViewModel: CitiesViewModel = hiltViewModel(),
    onClick: (String) -> Unit,
    coroutineScope: CoroutineScope,
    sheetState: ModalBottomSheetState,
    placeholder: String,
    isCitySheetContent: Boolean,
) {
    var searchValue by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(top = 15.dp)
    ) {
        SearchTextField(
            value = searchValue,
            onValueChange = { searchValue = it },
            placeholder = placeholder,
        )

        if (!citiesViewModel.citiesState.value.isLoading) {
            val cities: List<String> = if (isCitySheetContent) {
                val orase: List<String> = citiesViewModel.citiesState.value.cities.map { it.nume }

                orase.toSet().filter { it.contains(searchValue, ignoreCase = true) }
            }
            else {
                val judete: List<String> = citiesViewModel.citiesState.value.cities.map { it.judet }

                judete.toSet().filter { it.contains(searchValue, ignoreCase = true) }
            }

            LazyColumn() {
                items(cities.size) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onClick(cities.elementAt(it))
                                searchValue = ""
                                coroutineScope.launch {
                                    if (sheetState.isVisible)
                                        sheetState.hide()
                                    else
                                        sheetState.show()
                                }
                            }
                            .padding(vertical = 8.dp)
                    ) {
                        Text(
                            text = cities.elementAt(it),
                            style = MaterialTheme.typography.body2
                        )
                    }

                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
        }
        else
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(color = Purple700)
            }
    }
}