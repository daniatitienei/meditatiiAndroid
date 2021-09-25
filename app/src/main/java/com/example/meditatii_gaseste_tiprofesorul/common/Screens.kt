package com.example.meditatii_gaseste_tiprofesorul.common

sealed class Screens(val route: String) {
    object Categories: Screens("categories")
    object SelectedCategory: Screens("selectedCategory/{numeMaterie}")
    object InspectProfessor: Screens("inspectProfessor/{professor}")
    object Login: Screens("login")
    object Register: Screens("register")
}