package com.example.aplicaciontaller.View

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.aplicaciontaller.ViewModel.ContadorViewModel

@Composable
fun ContadorScreen(
    viewModel: ContadorViewModel,
    modifier: Modifier = Modifier
){
    Column(
        modifier=modifier
    ){
        //Mostramos en pantalla el valor actual del contador
        //Lo llamamos desde nuestra variable contador en el ViewModel
        Text(text="Contador ${viewModel.contador}")

        Button(
            onClick = {
                //La interfaz no modifica el contador
                //Solo le indica al viewModel que tiene que realizar
                viewModel.incrementar()
            }
        ) {
            Text("Incrementar")
        }
    }
}