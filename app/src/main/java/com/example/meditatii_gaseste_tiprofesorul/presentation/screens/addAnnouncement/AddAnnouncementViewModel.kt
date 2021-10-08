package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.addAnnouncement

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.meditatii_gaseste_tiprofesorul.common.Screens
import com.example.meditatii_gaseste_tiprofesorul.domain.model.Professor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AddAnnouncementViewModel @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
): ViewModel() {

    var materiiList = mutableStateListOf<String>()
        private set

    init {
        GlobalScope.launch(Dispatchers.IO) {
            fetchMaterii()
        }
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

    fun addAnnouncement(professor: Professor, navController: NavController) {
        firestore.collection("materii/${professor.materie}/anunturi")
            .document(professor.uuid)
            .set(professor)
            .addOnSuccessListener {
                navController.navigate(Screens.AnnouncementPosted.route) { launchSingleTop = true }
            }

        firestore.collection("materii").document(professor.materie)
            .get().addOnSuccessListener { document ->
                firestore.collection("materii/${professor.materie}/anunturi").addSnapshotListener { value, error ->
                    firestore.collection("materii").document(professor.materie)
                        .set(hashMapOf(
                            "anunturi" to value!!.size()
                        ), SetOptions.merge())
                }
            }

        firestore.collection("users/${auth.currentUser?.email}/anunturi").document(professor.uuid)
            .set(professor)
    }
}