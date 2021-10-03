package com.example.meditatii_gaseste_tiprofesorul.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cont(
    val isStudent: Boolean? = null,
    val profil: Map<String, String>? = null
) : Parcelable
