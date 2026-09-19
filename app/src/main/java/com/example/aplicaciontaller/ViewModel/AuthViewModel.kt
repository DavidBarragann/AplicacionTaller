package com.example.aplicaciontaller.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.google.firebase.auth.FirebaseAuthException


class AuthViewModel : ViewModel(){
    private val auth : FirebaseAuth = FirebaseAuth.getInstance()

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var errorMessage by mutableStateOf<String?>("")
    var isLoading by mutableStateOf(false)

    //Función de inicio de sesión

    fun login(onSuccess:() -> Unit){
        if(email.isEmpty() || password.isEmpty()){
            errorMessage = "Por favor, completa los campos."
            return
        }
        isLoading = true
        errorMessage = null

        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                task ->
                isLoading = false
                if(task.isSuccessful){
                    onSuccess()
                }else{
                    val e = task.exception
                    errorMessage = when (e){
                        is FirebaseAuthException -> {
                            when (e.errorCode){
                                "ERROR_INVALID_EMAIL" -> "El formato del correo es inválido."
                                "ERROR_WRONG_PASSWORD" -> "Contraseña incorrecta."
                                "ERROR_USER_NOT_FOUND" -> "El usuario no existe."
                                else -> "Error de autenticación: ${e.message}"
                            }
                        }
                        else -> "Ocurrió un error inesperado"
                    }
                }
            }
    }
    fun register(onSuccess: () -> Unit){
        if(email.isEmpty() || password.isEmpty()){
            errorMessage = "Por favor, completa los campos."
            return
        }
        isLoading = true
        errorMessage = null

        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                isLoading = false
                if(task.isSuccessful){
                    onSuccess()
                }else{
                    val e = task.exception
                    errorMessage = when (e) {
                        is FirebaseAuthException -> {
                            when (e.errorCode) {
                                "ERROR_WEAK_PASSWORD" -> "La contraseña es muy débil (mínimo 6 caracteres)."
                                "ERROR_EMAIL_ALREADY_IN_USE" -> "Este correo ya está registrado."
                                "ERROR_INVALID_EMAIL" -> "El formato del correo es inválido."
                                else -> "Error al registrar: ${e.message}"
                            }
                        }
                        else -> "Ocurrió un error inesperado."
                    }
                }
            }
    }
    fun clearError(){
        errorMessage= null
    }
    fun logout(onLogout:()-> Unit){
        auth.signOut()
        onLogout()
    }
}