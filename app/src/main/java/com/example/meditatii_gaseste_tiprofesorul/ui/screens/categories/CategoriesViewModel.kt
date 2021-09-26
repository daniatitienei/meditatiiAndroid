package com.example.meditatii_gaseste_tiprofesorul.ui.screens.categories

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.meditatii_gaseste_tiprofesorul.data.repository.categories.CategoriesRepository
import com.example.meditatii_gaseste_tiprofesorul.model.Materie
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val firestore: FirebaseFirestore
): ViewModel(), CategoriesRepository {
    var categoriesList = mutableStateListOf<Materie>()
        private set

    override suspend fun getMaterii() {
        firestore.collection("materii")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    Log.w("nu merge", "An error has occured", error)
                    return@addSnapshotListener
                }

                for (document in value!!.toObjects<Materie>()) {
//                    FIXME Da crash cand apesi pe fata de la info
                    categoriesList.add(document)
                }
            }
    }

    init {
        GlobalScope.launch(Dispatchers.IO) {
            getMaterii()
        }
    }
}
