package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.createProfessorProfile

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700
import com.example.meditatii_gaseste_tiprofesorul.common.FieldType
import com.example.meditatii_gaseste_tiprofesorul.common.Screens
import com.example.meditatii_gaseste_tiprofesorul.domain.model.ProfessorProfile
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.createProfessorProfile.components.SheetContent
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.register.components.InputField
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun CreateProfessorProfile(
    createProfessorProfileViewModel: CreateProfessorProfileViewModel = hiltViewModel(),
    auth: FirebaseAuth,
    navController: NavController
) {

    var lastName by remember {
        mutableStateOf("")
    }

    var firstName by remember {
        mutableStateOf("")
    }

    var phoneNumber by remember {
        mutableStateOf("")
    }

    var county by remember {
        mutableStateOf("")
    }

    var city by remember {
        mutableStateOf("")
    }

    val bitmap = remember {
        mutableStateOf<Bitmap?>(null)
    }

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val coroutineScope = rememberCoroutineScope()

    val citySheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val countySheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    val sheetShape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.White,
            darkIcons = true
        )
    }

    ModalBottomSheetLayout(
        sheetState = citySheetState,
        sheetContent = {
            SheetContent(
                onClick = { city = it },
                sheetState = citySheetState,
                coroutineScope = coroutineScope,
                placeholder = "Selectati un oras",
                isCitySheetContent = true
            )
        },
        sheetShape = sheetShape,
    ) {
        ModalBottomSheetLayout(
            sheetState = countySheetState,
            sheetContent = {
                SheetContent(
                    onClick = { county = it },
                    sheetState = countySheetState,
                    coroutineScope = coroutineScope,
                    placeholder = "Selectati un judet",
                    isCitySheetContent = false
                )
            },
            sheetShape = sheetShape,
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = "Creeaza-ti un profil de profesor") },
                        actions = {
                            IconButton(onClick = {
                                auth.signOut()
                                navController.navigate(Screens.Login.route)
                            }) {
                                Icon(Icons.Rounded.ExitToApp, contentDescription = null, tint = Purple700)
                            }
                        }
                    )
                }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(all = 20.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        val context = LocalContext.current

                        val launcher = rememberLauncherForActivityResult(
                            contract = ActivityResultContracts.GetContent()
                        ) { uri: Uri? ->
                            imageUri = uri
                        }

                        if (imageUri == null)
                            Row(
                                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
                            ) {
                                Box(
                                    Modifier.size(110.dp)
                                        .clip(CircleShape)
                                        .background(Purple700)
                                        .clickable { launcher.launch("image/*") }
                                ) {
                                    Icon(
                                        Icons.Rounded.Person,
                                        contentDescription = null,
                                        modifier = Modifier.size(60.dp).align(Alignment.Center),
                                        tint = Color.White
                                    )
                                }
                            }

                        imageUri?.let {
                            if (Build.VERSION.SDK_INT < 28) {
                                bitmap.value = MediaStore.Images
                                    .Media.getBitmap(context.contentResolver, it)

                            } else {
                                val source = ImageDecoder
                                    .createSource(context.contentResolver, it)
                                bitmap.value = ImageDecoder.decodeBitmap(source)
                            }

                            bitmap.value?.let { btm ->
                                Row(
                                    modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
                                ) {
                                    Box(
                                        modifier = Modifier.size(110.dp).clip(CircleShape).background(Purple700)
                                    ) {
                                        Image(
                                            bitmap = btm.asImageBitmap(),
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop
                                        )
                                    }
                                }
                            }
                        }
                        
                        Spacer(modifier = Modifier.height(10.dp))

                        InputField(
                            value = lastName,
                            onValueChange = { lastName = it},
                            placeholder = "Nume",
                            type = FieldType.BASIC_TEXT
                        )

                        InputField(
                            value = firstName,
                            onValueChange = { firstName = it },
                            placeholder = "Prenume",
                            type = FieldType.BASIC_TEXT
                        )

                        InputField(
                            value = phoneNumber,
                            onValueChange = { if (it.length <= 9) phoneNumber = it },
                            placeholder = "Numar de telefon",
                            leadingIcon = { Text(text = "+ 40") },
                            type = FieldType.PHONE_NUMBER
                        )

                        TextField(
                            value = city,
                            onValueChange = {},
                            enabled = false,
                            placeholder = { Text("Selectati un oras") },
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.Transparent,
                                focusedIndicatorColor = Purple700,
                            ),
                            modifier = Modifier.clickable {
                                coroutineScope.launch {
                                    if (citySheetState.isVisible)
                                        citySheetState.hide()
                                    else
                                        citySheetState.show()
                                }
                            }
                        )

                        TextField(
                            value = county,
                            onValueChange = {},
                            enabled = false,
                            placeholder = { Text("Selectati un judet") },
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.Transparent,
                                focusedIndicatorColor = Purple700,
                            ),
                            modifier = Modifier.clickable {
                                coroutineScope.launch {
                                    if (countySheetState.isVisible)
                                        countySheetState.hide()
                                    else
                                        countySheetState.show()
                                }
                            }
                        )
                    }

                    Button(
                        onClick = {
                            bitmap.value?.let { it1 ->
                                createProfessorProfileViewModel.uploadProfilePicture(
                                    bitmap = it1,
                                    professorProfile = ProfessorProfile(
                                        nume = lastName,
                                        prenume = firstName,
                                        judet = county,
                                        oras = city,
                                        numar = phoneNumber
                                    ),
                                    navController = navController
                                )
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Purple700
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        shape = CircleShape,
                        enabled = !(bitmap.value == null || county.isBlank() || firstName.trim().isBlank() || lastName.trim().isBlank() || city.isBlank() || phoneNumber.isBlank())
                    ) {
                        Text(text = "Creeaza profilul")
                    }
                }
            }
        }
    }
}
