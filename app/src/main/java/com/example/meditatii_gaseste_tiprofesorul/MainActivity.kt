package com.example.meditatii_gaseste_tiprofesorul

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import com.example.meditatii_gaseste_tiprofesorul.Screens.Register
import com.example.meditatii_gaseste_tiprofesorul.theme.MeditatiiTheme
import com.example.meditatii_gaseste_tiprofesorul.ui.theme.MeditatiiGasestetiProfesorulTheme

@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeditatiiTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Register()
                }
            }
        }
    }
}

