package com.example.meditatii_gaseste_tiprofesorul.presentation.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple500
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700

@Composable
fun MeditatiiTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        content = content,
        colors = LightColors,
        typography = MeditatiiTypography
    )
}

val LightColors = lightColors(
    primary = Color.White,
    primaryVariant = Purple500,
    error = Color.Red,
)

val MeditatiiTypography = Typography(
    h4 = TextStyle(
        color = Purple700,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    ),

    h3 = TextStyle(
        color = Purple700,
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold
    ),

    body2 = TextStyle(
        color = Purple700,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
    ),

    body1 = TextStyle(
        color = Purple700,
        fontSize = 16.sp,
    ),

    button = TextStyle(
        color = Color.White,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    ),
)