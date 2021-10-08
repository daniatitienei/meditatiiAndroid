package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.InspectProfessor

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.meditatii_gaseste_tiprofesorul.domain.model.Professor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InspectProfessorViewModel @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : ViewModel() {

    var favorite = mutableStateOf<Boolean>(false)
        private set

    fun checkFavorite(professor: Professor) {
        val docRef = firestore.collection("users/${auth.currentUser?.email!!}/favorite")

        docRef.addSnapshotListener { snapshot, error ->
            if (error != null)
                return@addSnapshotListener

            for (document in snapshot!!.toObjects<Professor>()) {
                if (professor.uuid == document.uuid) {
                    favorite.value = true
                    break
                }
            }
        }
    }

    fun addFavorite(professor: Professor) {
        val docRef = firestore.collection("users/${auth.currentUser?.email!!}/favorite").document(professor.uuid)

        docRef.set(professor)
    }

    fun removeFavorite(professor: Professor) {
        val docRef = firestore.collection("users/${auth.currentUser?.email!!}/favorite")
            .document(professor.uuid)
            .delete()

        favorite.value = false
    }
}