package com.example.meditatii_gaseste_tiprofesorul.ui.screens.account

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.meditatii_gaseste_tiprofesorul.data.repository.account.AccountInterface
import com.example.meditatii_gaseste_tiprofesorul.model.Cont
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
): ViewModel(), AccountInterface {

    var accountDetails = mutableStateOf(Cont())
        private set

    override suspend fun getAccountDetails() {
        auth.currentUser?.email?.let {
            firestore.collection("users")
                .document(it)
                .addSnapshotListener { snapshot, error ->
                    if (error != null) return@addSnapshotListener

                    if (snapshot != null && snapshot.exists()) {
                        val accountMap = snapshot.data

                        val account = Cont(
                            isStudent = accountMap?.get("isStudent") as Boolean,
                            profil = accountMap.get("profil") as Map<String, String>?
                        )
                        //                    TODO REFACTOR THE CODE

                        accountDetails.value = account
                    }
                }
        }
    }
}