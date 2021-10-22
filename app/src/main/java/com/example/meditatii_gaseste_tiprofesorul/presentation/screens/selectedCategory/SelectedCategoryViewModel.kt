package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.selectedCategory

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.meditatii_gaseste_tiprofesorul.domain.model.Professor
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObjects
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectedCategoryViewModel @Inject constructor(
    private val firestore: FirebaseFirestore
): ViewModel() {
    var profesoriList = MutableLiveData<List<Professor>>(listOf())
        private set

    var ordonare = mutableStateOf("Crescator")
    var oras = mutableStateOf("")

    fun getProfesoriList(numeMaterie: String) {
        val collectionRef =
            if (oras.value.isNotEmpty())
                firestore.collection("materii/$numeMaterie/anunturi")
                    .orderBy("pret", if (ordonare.value == "Crescator") Query.Direction.ASCENDING else Query.Direction.DESCENDING)
                    .whereEqualTo("oras", oras.value)
            else
                firestore.collection("materii/$numeMaterie/anunturi")

        collectionRef
            .addSnapshotListener { value, error ->
                if (error != null) {
                    Log.w("nu merge", "An error has occured", error)
                    return@addSnapshotListener
                }

                profesoriList.value = value!!.toObjects<Professor>()
            }
    }

}