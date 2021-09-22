package com.example.meditatii_gaseste_tiprofesorul.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.Categories
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.categories.CategoriesViewModel
import com.example.meditatii_gaseste_tiprofesorul.presentation.theme.MeditatiiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {
    private val categoriesViewModel by viewModels<CategoriesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        categoriesViewModel.getMaterii()

        setContent {
            MeditatiiTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Categories(categoriesViewModel)
                }
            }
        }
    }
}

