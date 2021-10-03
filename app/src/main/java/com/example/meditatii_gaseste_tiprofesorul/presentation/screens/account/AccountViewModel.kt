package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.account

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.meditatii_gaseste_tiprofesorul.domain.model.Cont
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
): ViewModel() {

    var accountDetails = mutableStateOf(Cont())
        private set

    fun getAccountDetails() {
            auth.currentUser?.email?.let {
                firestore.collection("users")
                    .document(it)
                    .addSnapshotListener { snapshot, error ->
                        if (error != null) return@addSnapshotListener

                        if (snapshot != null && snapshot.exists()) {
                            val accountMap = snapshot.data

                            val account = Cont(
                                isStudent = accountMap?.get("isStudent") as Boolean?,
                                profil = accountMap?.get("profil") as Map<String, String>?
                            )

                            accountDetails.value = account
                        }
                    }

        }
    }

    init {
        getAccountDetails()
    }
}