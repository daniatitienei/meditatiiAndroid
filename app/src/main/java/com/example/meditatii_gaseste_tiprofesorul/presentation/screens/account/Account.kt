package com.example.meditatii_gaseste_tiprofesorul.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.meditatii_gaseste_tiprofesorul.R
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700
import com.example.meditatii_gaseste_tiprofesorul.presentation.theme.MeditatiiTheme

@Composable
fun Account() {
    Scaffold(
        topBar = { AccountTopBar() }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.eu),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(150.dp)
                    .border(color = Purple700, shape = CircleShape, width = 2.dp)
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(text = "Atitienei Daniel", style = MaterialTheme.typography.h4)

            Spacer(modifier = Modifier.height(3.dp))
            Text(text = "Profesor")

            Spacer(modifier = Modifier.height(20.dp))

            TextListTile(text = "Setari cont", onClick = {})
            TextListTile(text = "Anunturile mele", onClick = {})
            TextListTile(text = "Deconectare", onClick = {})
        }
    }
}

@Composable
fun TextListTile(text: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .clickable { onClick() }
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = text)
        Spacer(modifier = Modifier.height(10.dp))
        Divider(
            color = Purple700,
            thickness = 1.dp,
        )
    }
}

@Composable
fun AccountTopBar() {
    TopAppBar(
        title = { Text(text = "Cont") },
        elevation = 5.dp,
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    Icons.Rounded.ArrowBack,
                    contentDescription = null,
                    tint = Purple700
                )
            }
        },
    )
}

@Preview (showBackground = true)
@Composable
fun AccountPreview() {
    MeditatiiTheme {
        Account()
    }
}