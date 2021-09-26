package com.example.meditatii_gaseste_tiprofesorul.ui.screens.InspectProfessor

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.meditatii_gaseste_tiprofesorul.colors.Purple700
import com.example.meditatii_gaseste_tiprofesorul.domain.model.Professor
import com.example.meditatii_gaseste_tiprofesorul.ui.screens.InspectProfessor.components.ContactTile
import com.example.meditatii_gaseste_tiprofesorul.ui.screens.InspectProfessor.components.InspectProfessorTopBar
import com.example.meditatii_gaseste_tiprofesorul.ui.screens.InspectProfessor.components.ProfessorCard
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun InspectProfessor(professor: Professor, navController: NavController) {
    var descriptionIsExpanded by remember {
        mutableStateOf(false)
    }

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Purple700,
            darkIcons = false
        )
    }

    Scaffold(
        topBar = { InspectProfessorTopBar(navController) }
    ) {
        Column(
            modifier = Modifier.fillMaxHeight()
        ) {
            ProfessorCard(
                profesor = professor,
                modifier = Modifier
                    .weight(1.1f)
                    .clip(
                        RoundedCornerShape(
                            bottomEnd = 15.dp,
                            bottomStart = 15.dp
                        )
                    )
                    .background(Purple700)
                    .padding(horizontal = 20.dp)
            )
            Box(
                modifier = Modifier
                    .weight(3.3f)
                    .background(Color.White)
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(start = 20.dp, end = 20.dp, bottom = 30.dp)
                ) {
                    item {
                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = "Despre ${professor.nume} ${professor.prenume}",
                            style = MaterialTheme.typography.body2
                        )

                        Spacer(modifier = Modifier.height(5.dp))

                        Text(
                            text = professor.descriere,
                            maxLines = if (descriptionIsExpanded) Int.MAX_VALUE else 4,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.clickable { descriptionIsExpanded = !descriptionIsExpanded },
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(25.dp))
                        
                        Text(
                            text = "Pret pe sesiune",
                            style = MaterialTheme.typography.body2
                        )

                        Spacer(modifier = Modifier.height(5.dp))

                        Text(text = "${professor.pret} LEI")
                    }
                    
                    item {
                        Spacer(modifier = Modifier.height(25.dp))

                        Text(
                            text = "Contact",
                            style = MaterialTheme.typography.body2
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Column() {
                            ContactTile(text = professor.numar, isPhone = true)
                            Spacer(modifier = Modifier.height(5.dp))
                            ContactTile(text = professor.email)
                        }
                    }
                }
            }
        }
    }
}