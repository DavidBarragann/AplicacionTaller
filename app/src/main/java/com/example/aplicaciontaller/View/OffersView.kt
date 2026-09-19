package com.example.aplicaciontaller.View

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.aplicaciontaller.Components.MainScaffold
import com.example.aplicaciontaller.Components.Title

@Composable
fun OffersView(onItemSelected: (Int) -> Unit) {
    MainScaffold(
        selectedItem = 1,
        onItemSelected = onItemSelected,
        topBar = { Title(text = "Ofertas Especiales") }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "No hay ofertas disponibles", fontSize = 20.sp)
        }
    }
}
