package com.example.meditatii_gaseste_tiprofesorul.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.ImageLoader
import com.example.meditatii_gaseste_tiprofesorul.common.Screens
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.account.AccountViewModel
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.account.components.AccountTopBar
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.account.components.TextListTile
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.categories.components.ProfessorExtra
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Account(
    navController: NavController,
    auth: FirebaseAuth,
    accountViewModel: AccountViewModel,
    svgLoader: ImageLoader,
) {
    val scope = rememberCoroutineScope()

//    scope.launch {
//        accountViewModel.getAccountDetails()
//    }

    Scaffold(
        topBar = { AccountTopBar(navController = navController) }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (accountViewModel.accountDetails.value.isStudent == false) ProfessorExtra(accountViewModel, svgLoader)

            Text(text = if (accountViewModel.accountDetails.value.isStudent == true) "Student" else "Profesor")

            Spacer(modifier = Modifier.height(20.dp))

            TextListTile(text = "Setari cont", onClick = {})
            if (accountViewModel.accountDetails.value.isStudent == false) TextListTile(text = "Anunturile mele", onClick = {})
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


