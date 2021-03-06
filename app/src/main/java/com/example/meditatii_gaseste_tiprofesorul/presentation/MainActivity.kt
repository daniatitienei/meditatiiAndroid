package com.example.meditatii_gaseste_tiprofesorul.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import com.example.meditatii_gaseste_tiprofesorul.Navigation
import com.example.meditatii_gaseste_tiprofesorul.presentation.theme.MeditatiiTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.moshi.Moshi
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalCoilApi
@AndroidEntryPoint
@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {
    @Inject lateinit var auth: FirebaseAuth
    @Inject lateinit var moshi: Moshi
    @Inject lateinit var svgLoader: ImageLoader
    @Inject lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MeditatiiTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Navigation(
                        auth = auth,
                        firestore = firestore,
                        moshi = moshi,
                        svgLoader = svgLoader,
                    )
                }
            }
        }
    }
}

