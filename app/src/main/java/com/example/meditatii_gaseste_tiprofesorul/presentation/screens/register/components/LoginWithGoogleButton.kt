package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.register.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700
import com.free.find_your_teacher.R

@Composable
fun LoginWithGoogleButton() {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier.fillMaxWidth(),
        shape = CircleShape,
    ) {
        Image(
            painter = painterResource(id = R.drawable.google),
            contentDescription = "Continua cu Google",
            modifier = Modifier.size(26.dp)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = "Continua cu Google",
            color = Purple700,
        )
    }
}
