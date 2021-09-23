package com.example.meditatii_gaseste_tiprofesorul.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import coil.annotation.ExperimentalCoilApi
import com.example.meditatii_gaseste_tiprofesorul.Navigation
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.categories.CategoriesViewModel
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.selectedCategory.SelectedCategoryViewModel
import com.example.meditatii_gaseste_tiprofesorul.presentation.theme.MeditatiiTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalCoilApi
@AndroidEntryPoint
@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {
    private val categoriesViewModel by viewModels<CategoriesViewModel>()
    private val selectedCategoryViewModel by viewModels<SelectedCategoryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MeditatiiTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Navigation(
                        categoriesViewModel = categoriesViewModel,
                        selectedCategoryViewModel = selectedCategoryViewModel,
                    )
                }
            }
        }
    }
}

