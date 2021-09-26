package com.example.meditatii_gaseste_tiprofesorul.ui.screens.selectedCategory

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.meditatii_gaseste_tiprofesorul.domain.model.Professor
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectedCategoryViewModel @Inject constructor(
    private val firestore: FirebaseFirestore
): ViewModel() {
    var profesoriList = mutableStateListOf<Professor>()
        private set

    fun getProfesoriList(numeMaterie: String) {
        profesoriList.clear()
        firestore.collection("materii/$numeMaterie/anunturi")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    Log.w("nu merge", "An error has occured", error)
                    return@addSnapshotListener
                }

                for (document in value!!.toObjects<Professor>()) {
                    profesoriList.add(document)
                }
            }
    }
}