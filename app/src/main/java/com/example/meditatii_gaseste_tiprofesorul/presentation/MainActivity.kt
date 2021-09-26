package com.example.meditatii_gaseste_tiprofesorul.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import coil.annotation.ExperimentalCoilApi
import com.example.meditatii_gaseste_tiprofesorul.Navigation
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.categories.CategoriesViewModel
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.login.LoginViewModel
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.register.RegisterViewModel
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.selectedCategory.SelectedCategoryViewModel
import com.example.meditatii_gaseste_tiprofesorul.presentation.theme.MeditatiiTheme
import com.google.firebase.auth.FirebaseAuth
import com.squareup.moshi.Moshi
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalMaterialApi
@ExperimentalCoilApi
@AndroidEntryPoint
@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {
    @Inject lateinit var auth: FirebaseAuth
    @Inject lateinit var moshi: Moshi

    private val categoriesViewModel by viewModels<CategoriesViewModel>()
    private val selectedCategoryViewModel by viewModels<SelectedCategoryViewModel>()
    private val registerViewModel by viewModels<RegisterViewModel>()
    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MeditatiiTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Navigation(
                        categoriesViewModel = categoriesViewModel,
                        selectedCategoryViewModel = selectedCategoryViewModel,
                        registerViewModel = registerViewModel,
                        loginViewModel = loginViewModel,
                        auth = auth,
                        moshi = moshi,
                    )
                }
            }
        }
    }
}

