package com.example.aplicaciontaller.View

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aplicaciontaller.Components.MainScaffold
import com.example.aplicaciontaller.Components.Title
import com.example.aplicaciontaller.ViewModel.AuthViewModel

//import com.example.aplicaciontaller.ViewModel.AuthViewModel

/**
 * Vista de Perfil
 * @param onItemSelected Maneja la navegación del menú inferior.
 * @param onLogout Acción para cerrar sesión y volver al login.
 */
@Composable
fun ProfileView(
    onItemSelected: (Int) -> Unit,
    onLogout: () -> Unit = {}
) {
    val authVM: AuthViewModel = viewModel()

    MainScaffold(
        selectedItem = 3,
        onItemSelected = onItemSelected,
        topBar = { Title(text = "Perfil de Usuario") }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Información del usuario", fontSize = 20.sp)
            
            Spacer(modifier = Modifier.height(32.dp))

            // Botón para cerrar sesión
            Button(
                onClick = { authVM.logout(onLogout) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                Text("Cerrar Sesión", color = MaterialTheme.colorScheme.onError)
            }
        }
    }
}
