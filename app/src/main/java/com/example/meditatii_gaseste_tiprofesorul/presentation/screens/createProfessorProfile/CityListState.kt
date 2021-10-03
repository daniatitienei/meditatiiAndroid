package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.createProfessorProfile

import com.example.meditatii_gaseste_tiprofesorul.domain.model.City

data class CityListState(
    val cities: List<City> = emptyList(),
    val error: String = "",
    val isLoading: Boolean = false
)
