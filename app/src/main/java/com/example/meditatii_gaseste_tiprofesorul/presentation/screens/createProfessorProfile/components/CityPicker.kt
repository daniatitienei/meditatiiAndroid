package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.createProfessorProfile.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.createProfessorProfile.CitiesViewModel

@ExperimentalMaterialApi
@Composable
fun CityPicker(
    state: BottomSheetScaffoldState,
    citiesViewModel: CitiesViewModel = hiltViewModel()
) {
    BottomSheetScaffold(
        scaffoldState = state,
        sheetContent = {
            LazyColumn() {
                items(citiesViewModel.citiesState.value.cities.size) {
                    Text(text = citiesViewModel.citiesState.value.cities[it].nume)
                }
            }
        },
        sheetPeekHeight = 0.dp
    ) {}

}