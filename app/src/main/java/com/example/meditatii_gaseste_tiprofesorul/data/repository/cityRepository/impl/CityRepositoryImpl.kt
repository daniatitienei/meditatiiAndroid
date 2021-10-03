package com.example.meditatii_gaseste_tiprofesorul.data.repository.cityRepository.impl

import com.example.meditatii_gaseste_tiprofesorul.data.remote.CityApi
import com.example.meditatii_gaseste_tiprofesorul.data.remote.dto.CityDTO
import com.example.meditatii_gaseste_tiprofesorul.data.repository.cityRepository.CityRepository
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val api: CityApi
): CityRepository {

    override suspend fun getCities(): List<CityDTO> = api.getCities()
}