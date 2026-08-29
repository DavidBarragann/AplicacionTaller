package com.example.aplicaciontaller.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class ContadorViewModel: ViewModel() {
    //mutableStateOf indica que cuando el valor cambia, compose debe actualizarse
    var contador by mutableStateOf(0)
        //Indica que el valor solo puede modificarse en el ViewModel
        private set
    //Lógica de incremento
    fun incrementar(){
        contador++
    }
}