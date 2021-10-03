package com.example.meditatii_gaseste_tiprofesorul.data.repository.cityRepository.use_case

import com.example.meditatii_gaseste_tiprofesorul.common.Resource
import com.example.meditatii_gaseste_tiprofesorul.data.remote.dto.toCity
import com.example.meditatii_gaseste_tiprofesorul.data.repository.cityRepository.CityRepository
import com.example.meditatii_gaseste_tiprofesorul.domain.model.City
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(
    private val repository: CityRepository
) {

    operator fun invoke(): Flow<Resource<List<City>>> = flow {
        try {
            emit(Resource.Loading<List<City>>())

            val cities = repository.getCities().map { it.toCity() }

            emit(Resource.Success<List<City>>(cities))
        } catch (e: HttpException) {
            emit(Resource.Error<List<City>>(e.localizedMessage ?: "An unexpected error has occured."))
        } catch (e: IOException) {
            emit(Resource.Error<List<City>>("No internet. Please check your connection"))
        }
    }
}