package com.example.meditatii_gaseste_tiprofesorul.data.repository.register

interface RegisterRepository {
    fun registerWithEmailAndPassword(email: String, password: String)
}