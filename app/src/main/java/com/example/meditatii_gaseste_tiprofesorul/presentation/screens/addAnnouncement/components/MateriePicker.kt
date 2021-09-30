package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.addAnnouncement.components

import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.meditatii_gaseste_tiprofesorul.presentation.screens.addAnnouncement.AddAnnouncementViewModel

@ExperimentalMaterialApi
@Composable
fun MateriePicker(
    expanded: Boolean,
    addAnnouncementViewModel: AddAnnouncementViewModel,
    onDismissRequest: () -> Unit,
    onSelected: (String) -> Unit,
) {
    DropdownMenu(expanded = expanded, onDismissRequest = onDismissRequest) {
        addAnnouncementViewModel.materiiList.forEach { materie ->
            DropdownMenuItem(onClick = { onSelected(materie) }) {
                Text(text = materie)
            }
        }
    }
}
