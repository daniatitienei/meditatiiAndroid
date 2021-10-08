package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.myAnnouncements

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.meditatii_gaseste_tiprofesorul.domain.model.Professor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyAnnouncementsViewModel @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth,
    private val storage: FirebaseStorage
) : ViewModel() {

    var anunturi = mutableStateListOf<Professor>()
        private set

    init {
        fetchMyAnnouncements()
    }

    fun fetchMyAnnouncements() {
        val docRef = firestore.collection("users/${auth.currentUser?.email!!}/anunturi")

        docRef.addSnapshotListener { snapshot, error ->
            if (error != null)
                return@addSnapshotListener

            for (document in snapshot!!.toObjects<Professor>()) {
                anunturi.add(document)
            }
        }
    }

    fun removeMyAnnouncement(professor: Professor) {
        firestore.collection("users/${auth.currentUser?.email!!}/anunturi")
            .document(professor.uuid)
            .delete()

        firestore.collection("materii/${professor.materie}/anunturi")
            .document(professor.uuid)
            .delete()

        firestore.collection("materii").document(professor.materie)
            .get().addOnSuccessListener { document ->
                firestore.collection("materii").document(professor.materie)
                    .get().addOnSuccessListener { document ->
                        firestore.collection("materii/${professor.materie}/anunturi").addSnapshotListener { value, error ->
                            firestore.collection("materii").document(professor.materie)
                                .set(hashMapOf(
                                    "anunturi" to value!!.size()
                                ), SetOptions.merge())
                        }
                    }
            }

//        Deleting announce from all users favorites
        firestore.collection("users").addSnapshotListener { snapshot, error ->
            if (error != null)
                return@addSnapshotListener

            for (user in snapshot!!) {
                firestore.collection("users/${user.id}/favorite").document(professor.uuid).delete()
            }
        }

        anunturi.clear()
        fetchMyAnnouncements()
    }
}