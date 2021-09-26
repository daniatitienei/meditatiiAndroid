package com.example.meditatii_gaseste_tiprofesorul.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Materie(
    val nume: String = "",
    val imageUrl: String = "",
    val anunturi: Int = 0,
    val backgroundColor: Long = 0,
    val circleColor: Long = 0
) : Parcelable
