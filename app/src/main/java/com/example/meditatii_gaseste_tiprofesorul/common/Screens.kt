package com.example.meditatii_gaseste_tiprofesorul.common

sealed class Screens(val route: String) {
    object Categories: Screens("categories")
    object SelectedCategory: Screens("selectedCategory/{numeMaterie}")
}