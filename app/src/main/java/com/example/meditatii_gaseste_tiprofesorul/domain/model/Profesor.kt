package com.example.meditatii_gaseste_tiprofesorul.domain.model

import com.google.firebase.Timestamp

data class Profesor(
    val date: Timestamp = Timestamp.now(),
    val descriere: String = "",
    val email: String = "",
    val imgUrl: String = "",
    val judet: String = "",
    val materie: String = "",
    val numar: String = "",
    val nume: String = "",
    val prenume: String = "",
    val oras: String = "",
    val pret: Int = 0,
    val tag: String = "",
    val uuid: String = "",
)
