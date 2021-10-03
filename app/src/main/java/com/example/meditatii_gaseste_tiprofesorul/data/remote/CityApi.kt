package com.example.meditatii_gaseste_tiprofesorul.data.remote

import com.example.meditatii_gaseste_tiprofesorul.data.remote.dto.CityDTO
import retrofit2.http.GET

interface CityApi {

    @GET("/catalin87/baza-de-date-localitati-romania/master/date/localitati.json")
    suspend fun getCities(): List<CityDTO>
}