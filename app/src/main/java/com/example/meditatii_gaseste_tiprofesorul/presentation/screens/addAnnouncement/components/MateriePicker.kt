package com.example.meditatii_gaseste_tiprofesorul.presentation.screens.addAnnouncement.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun MateriePicker(
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    scope: CoroutineScope
) {
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            Box(
                modifier = Modifier.fillMaxWidth()
                    .fillMaxHeight(0.5f)
            ) {
                LazyColumn {
                    items(20) {
                        Text("amogus $it")
                    }
                }
            }
        },
        sheetPeekHeight = 0.dp
    ) {
        Button(onClick = {
            scope.launch {
                if (bottomSheetScaffoldState.bottomSheetState.isCollapsed)
                    bottomSheetScaffoldState.bottomSheetState.expand()
                else
                    bottomSheetScaffoldState.bottomSheetState.collapse()
            }
        }) {
            Text("click")
        }
    }
}