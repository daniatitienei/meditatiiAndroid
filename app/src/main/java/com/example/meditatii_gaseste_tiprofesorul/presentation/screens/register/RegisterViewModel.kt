package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.register

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.meditatii_gaseste_tiprofesorul.common.Screens
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val auth: FirebaseAuth,
): ViewModel() {
    var emailError = mutableStateOf<String>("")
        private set

    var passwordError = mutableStateOf<String>("")
        private set

    fun registerWithEmailAndPassword(email: String, password: String, navController: NavController) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("cont", "createUser: success")
                    auth.signInWithEmailAndPassword(email, password)

                    navController.navigate(Screens.SelectRole.route) {
                        launchSingleTop = true
                    }
                } else {
                    Log.d("cont", "createUser: failed")
                    try {
                        throw task.exception!!
                    } catch (e: FirebaseAuthWeakPasswordException) {
                        Log.d("cont", "Parola este prea slaba")
                        passwordError.value = "Parola este prea slaba"
                    } catch (e: FirebaseAuthUserCollisionException) {
                        Log.d("cont", "Email-ul deja este folosit")
                        emailError.value = "Email-ul deja este folosit"
                    }
                }
            }
    }

    fun registerWithGoogle(
        credential: AuthCredential,
        navController: NavController
    ) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("GoogleSignIn", "signInWithCredential:success")
                    navController.navigate(Screens.SelectRole.route) { launchSingleTop = true }
                }
                else
                    Log.d("GoogleSignIn", "signInWithCredential:failed")
            }
    }
}


