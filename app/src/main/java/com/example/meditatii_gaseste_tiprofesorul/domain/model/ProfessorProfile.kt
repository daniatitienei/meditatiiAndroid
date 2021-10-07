package com.example.meditatii_gaseste_tiprofesorul.domain.model

data class ProfessorProfile(
    val nume: String,
    val prenume: String,
    val numar: String,
    val judet: String,
    val oras: String,
    var imgUrl: String = ""
)
