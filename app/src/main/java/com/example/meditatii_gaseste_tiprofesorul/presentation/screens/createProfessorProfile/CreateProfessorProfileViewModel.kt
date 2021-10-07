package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.createProfessorProfile

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import com.example.meditatii_gaseste_tiprofesorul.domain.model.ProfessorProfile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.ByteArrayOutputStream
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CreateProfessorProfileViewModel @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth,
    private val storage: FirebaseStorage
): ViewModel() {

    fun uploadProfilePicture(bitmap: Bitmap, professorProfile: ProfessorProfile) {

        val baos = ByteArrayOutputStream()

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        val uuid = UUID.randomUUID()

        val storageRef = storage.reference.child("poze_profesori/${uuid.toString()}")

        var uploadTask = storageRef.putBytes(data)


        val urlTask = uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            storageRef.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUrl = task.result

                professorProfile.imgUrl = downloadUrl.toString()

                createProfessorProfile(
                    professorProfile = professorProfile
                )
            }
        }
    }

    fun createProfessorProfile(
        professorProfile: ProfessorProfile
    ) {

        val data = hashMapOf(
            "nume" to professorProfile.nume,
            "prenume" to professorProfile.prenume,
            "numar" to "0${professorProfile.numar}",
            "judet" to professorProfile.judet,
            "oras" to professorProfile.oras,
            "imgUrl" to professorProfile.imgUrl
        )

        firestore.collection("users").document(auth.currentUser?.email!!)
            .set(hashMapOf(
                "profil" to data
            ), SetOptions.merge())
    }
}