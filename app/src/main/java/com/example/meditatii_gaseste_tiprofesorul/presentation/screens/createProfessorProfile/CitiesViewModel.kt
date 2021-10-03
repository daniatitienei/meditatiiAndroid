package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.createProfessorProfile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditatii_gaseste_tiprofesorul.common.Resource
import com.example.meditatii_gaseste_tiprofesorul.data.repository.cityRepository.use_case.GetCitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(
    private val getCitiesUseCase: GetCitiesUseCase
): ViewModel() {

    var citiesState = mutableStateOf(CityListState())
        private set

    init {
        getCities()
    }

    private fun getCities() {
        getCitiesUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> citiesState.value = CityListState(cities = result.data!!)
                is Resource.Error -> citiesState.value = CityListState(error = result.message!!)
                is Resource.Loading -> citiesState.value = CityListState(isLoading = true)
            }
        }.launchIn(viewModelScope)
    }
}