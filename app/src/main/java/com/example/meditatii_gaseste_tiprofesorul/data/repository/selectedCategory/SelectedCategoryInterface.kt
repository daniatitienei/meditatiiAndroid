package com.example.meditatii_gaseste_tiprofesorul.data.repository.selectedCategory

interface SelectedCategoryInterface {
    suspend fun getProfesoriList(numeMaterie: String)
}