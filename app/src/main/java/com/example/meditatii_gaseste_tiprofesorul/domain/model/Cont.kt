package com.example.meditatii_gaseste_tiprofesorul.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cont(
    val isStudent: Boolean = true,
    val profil: Map<String, String>? = mapOf()
) : Parcelable
