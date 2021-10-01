package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.account

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.meditatii_gaseste_tiprofesorul.data.model.Cont
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
): ViewModel() {

    var accountDetails = mutableStateOf(Cont())
        private set

    suspend fun getAccountDetails() {
        withContext(Dispatchers.Main) {
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
    }

    init {
        GlobalScope.launch(Dispatchers.IO) {
            getAccountDetails()
        }
    }
}