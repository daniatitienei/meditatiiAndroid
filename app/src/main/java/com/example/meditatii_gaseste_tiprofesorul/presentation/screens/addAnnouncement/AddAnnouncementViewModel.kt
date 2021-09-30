package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.addAnnouncement

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class AddAnnouncementViewModel: ViewModel() {
    var descriptionError = mutableStateOf<String>("")
        private set

    fun validateDescription(it: String) {
        if (it.isBlank())
            descriptionError.value = "Descrierea trebuie completata"
    }
}