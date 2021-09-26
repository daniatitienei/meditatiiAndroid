package com.example.meditatii_gaseste_tiprofesorul.data.repository.login

interface LoginRepository {
    fun loginWithEmailAndPassword(email: String, password: String)
}