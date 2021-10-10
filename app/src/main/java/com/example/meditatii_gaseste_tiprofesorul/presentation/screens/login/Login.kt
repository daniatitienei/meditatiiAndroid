package com.example.meditatii_gaseste_tiprofesorul.presentation.screens

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700
import com.example.meditatii_gaseste_tiprofesorul.common.FieldType
import com.example.meditatii_gaseste_tiprofesorul.common.Screens
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.account.AccountViewModel
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.login.LoginViewModel
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.register.components.InputField
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.register.components.LoginWithGoogleButton
import com.example.meditatii_gaseste_tiprofesorul.startDestination
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

@ExperimentalComposeUiApi
@Composable
fun Login(
    navController: NavController,
    svgLoader: ImageLoader,
    auth: FirebaseAuth,
    loginViewModel: LoginViewModel = hiltViewModel(),
    accountViewModel: AccountViewModel = hiltViewModel()
) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.White,
            darkIcons = true
        )
    }

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        try {
            val account = task.getResult(ApiException::class.java)!!
            val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)
            loginViewModel.loginWithGoogle(credential, navController)
        } catch (e: ApiException) {
            Log.w("TAG", "Google sign in failed", e)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Conectare", style = MaterialTheme.typography.h3)
        CompositionLocalProvider(LocalImageLoader provides svgLoader) {
            val imageLink = "https://firebasestorage.googleapis.com/v0/b/gaseste-ti-profesorul.appspot.com/o/svg%2Fusers_profile.png?alt=media&token=afa9927a-83d4-4255-b6bb-0e8f7fe3ea8d"
            val painter = rememberImagePainter(
                data = imageLink,
                builder = {
                    crossfade(true)
                }
            )

            Image(
                painter = painter,
                modifier = Modifier.size(200.dp),
                contentDescription = null,
            )
        }

        Column(
            modifier = Modifier.padding(top = 20.dp),
            horizontalAlignment = Alignment.End,
        ) {
            InputField(
                value = email,
                onValueChange = { email = it },
                placeholder = "Email",
                error = loginViewModel.emailError.value,
                type = FieldType.EMAIL
            )

            Spacer(modifier = Modifier.height(10.dp))

            InputField(
                value = password,
                onValueChange = { password = it },
                placeholder = "Parola",
                error = loginViewModel.passwordError.value,
                type = FieldType.PASSWORD
            )

            TextButton(onClick = {
                navController.navigate(Screens.Register.route) {
                    launchSingleTop = true
                }
            }, ) {
                Text(
                    text = "Nu am cont",
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold,
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = {
                loginViewModel.loginWithEmailAndPassword(email.trim(), password.trim()) {
                    navController.navigate(
                        startDestination(
                            accountViewModel = accountViewModel,
                            auth = auth
                        )
                    ) {
                        launchSingleTop = true
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Purple700
            )
        ) {
            Text(text = "Conectare")
        }

        Spacer(modifier = Modifier.height(5.dp))

        Text("SAU")

        Spacer(modifier = Modifier.height(5.dp))

        val context = LocalContext.current
        val token = "834293774562-3o8ppt3dp1dct3e5q773jks56f2e90u1.apps.googleusercontent.com"

        LoginWithGoogleButton(
            onClick = {
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(token)
                    .requestEmail()
                    .build()

                val googleSignInClient = GoogleSignIn.getClient(context, gso)
                launcher.launch(googleSignInClient.signInIntent)
            }
        )
    }
}