package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.categories

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.meditatii_gaseste_tiprofesorul.domain.model.Materie
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val firestore: FirebaseFirestore
): ViewModel() {
    var categoriesList = mutableStateListOf<Materie>()
        private set

    fun fetchMaterii() {
            firestore.collection("materii")
                .addSnapshotListener { value, error ->
                    if (error != null) {
                        Log.w("nu merge", "An error has occured", error)
                        return@addSnapshotListener
                    }

                    for (document in value!!.toObjects<Materie>()) {
                        categoriesList.add(document)
                    }
                }

    }

    init {
        fetchMaterii()
    }
}
