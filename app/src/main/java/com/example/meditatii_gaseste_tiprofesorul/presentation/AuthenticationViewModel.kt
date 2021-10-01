package com.example.meditatii_gaseste_tiprofesorul.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class AuthenticationViewModel: ViewModel() {
    var isLoggedIn = mutableStateOf(false)
        private set

    fun updateUI(newValue: Boolean) {
        isLoggedIn.value = newValue
    }
}