package com.example.meditatii_gaseste_tiprofesorul.ui.screens.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.meditatii_gaseste_tiprofesorul.data.repository.login.LoginRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val auth: FirebaseAuth
): ViewModel(), LoginRepository {
    var emailError = mutableStateOf<String>("")
        private set

    var passwordError = mutableStateOf<String>("")
        private set

    override fun loginWithEmailAndPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("cont", "loginUser: success")
                } else {
                    Log.d("cont", "loginUser: failed")

                    try {
                        throw task.exception!!
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        Log.d("cont", "Parola este gresita")
                        passwordError.value = "Parola este gresita"

//                        FIXME te redirectioneaza chiar daca da fail

                    } catch (e: FirebaseAuthInvalidUserException) {
                        Log.d("cont", "Email-ul este invalid")
                        emailError.value = "Contul nu exista"
                    }
                }
            }
    }
}
