package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.favorites

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.meditatii_gaseste_tiprofesorul.domain.model.Professor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
): ViewModel() {

    var favorites = mutableStateListOf<Professor>()
        private set

    init {
        fetchFavorites()
    }

    fun fetchFavorites() {
        val docRef = firestore.collection("users/${auth.currentUser?.email!!}/favorite")

        docRef.addSnapshotListener { snapshot, error ->
            if (error != null)
                return@addSnapshotListener

            for (document in snapshot!!.toObjects<Professor>()) {
                favorites.add(document)
            }
        }
    }
}