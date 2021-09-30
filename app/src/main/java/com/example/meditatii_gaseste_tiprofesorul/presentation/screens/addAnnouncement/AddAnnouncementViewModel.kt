package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.addAnnouncement

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AddAnnouncementViewModel @Inject constructor(
    private val firestore: FirebaseFirestore
): ViewModel() {
    var descriptionError = mutableStateOf<String>("")
        private set

    var materiiList = mutableStateListOf<String>()
        private set

    fun validateDescription(it: String) {
        if (it.isBlank())
            descriptionError.value = "Descrierea trebuie completata"
    }

    suspend fun fetchMaterii() {
        withContext(Dispatchers.Main) {
            firestore.collection("materii").addSnapshotListener { snapshot, error ->
                if (error != null) return@addSnapshotListener

                for (document in snapshot!!.documents)
                    materiiList.add(document.id)
            }
        }
    }

    suspend fun addAnnouncement() {
        // TODO Sa fac functia
    }

    init {
        GlobalScope.launch(Dispatchers.IO) {
            fetchMaterii()
        }
    }
}