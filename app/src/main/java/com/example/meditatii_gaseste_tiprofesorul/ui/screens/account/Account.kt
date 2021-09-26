package com.example.meditatii_gaseste_tiprofesorul.ui.screens

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700
import com.example.meditatii_gaseste_tiprofesorul.common.Screens
import com.example.meditatii_gaseste_tiprofesorul.ui.screens.account.AccountViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Account(
    navController: NavController,
    auth: FirebaseAuth,
    accountViewModel: AccountViewModel
) {
    accountViewModel.getAccount()

    Scaffold(
        topBar = { AccountTopBar(navController = navController) }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (!accountViewModel.accountDetails.value.isStudent) {
                val imageLoader = ImageLoader.Builder(LocalContext.current)
                    .componentRegistry {
                        add(SvgDecoder(LocalContext.current))
                    }
                    .build()

                val profil = accountViewModel.accountDetails.value.profil

                val painter = rememberImagePainter(
                    data = profil?.get("imgUrl"),
                    builder = {
                        crossfade(true)
                    }
                )

                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(150.dp)
                        .border(color = Purple700, shape = CircleShape, width = 2.dp)
                )


                Spacer(modifier = Modifier.height(15.dp))

                Text(text = "${profil?.get("nume")!!} ${profil.get("prenume")}", style = MaterialTheme.typography.h4)

                Spacer(modifier = Modifier.height(3.dp))
            }

            Text(text = if (accountViewModel.accountDetails.value.isStudent) "Student" else "Profesor")

            Spacer(modifier = Modifier.height(20.dp))

            TextListTile(text = "Setari cont", onClick = {})
            if (!accountViewModel.accountDetails.value.isStudent) TextListTile(text = "Anunturile mele", onClick = {})
            TextListTile(text = "Favorite", onClick = {})
            TextListTile(text = "Deconectare", onClick = {
                auth.signOut()
                navController.navigate(Screens.Login.route) {
                    launchSingleTop = true
                }
            })
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
fun AccountTopBar(navController: NavController) {
    TopAppBar(
        title = { Text(text = "Cont") },
        elevation = 5.dp,
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    Icons.Rounded.ArrowBack,
                    contentDescription = null,
                    tint = Purple700
                )
            }
        },
    )
}