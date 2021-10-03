package com.example.meditatii_gaseste_tiprofesorul.data.remote.dto

import com.example.meditatii_gaseste_tiprofesorul.domain.model.City

data class CityDTO(
    val auto: String,
    val diacritice: String,
    val id: Int,
    val judet: String,
    val lat: Double,
    val lng: Double,
    val nume: String,
    val populatie: Int,
    val zip: Int
)

fun CityDTO.toCity(): City = City(nume, judet)