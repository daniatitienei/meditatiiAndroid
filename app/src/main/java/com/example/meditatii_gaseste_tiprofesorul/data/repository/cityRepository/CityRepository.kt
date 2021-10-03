package com.example.meditatii_gaseste_tiprofesorul.data.repository.cityRepository

import com.example.meditatii_gaseste_tiprofesorul.data.remote.dto.CityDTO

interface CityRepository {

    suspend fun getCities(): List<CityDTO>
}