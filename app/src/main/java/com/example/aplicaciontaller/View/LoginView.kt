package com.example.aplicaciontaller.View

import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aplicaciontaller.Components.spaceV
import com.example.aplicaciontaller.ViewModel.AuthViewModel
import com.example.aplicaciontaller.ui.theme.AplicacionTallerTheme

@Composable
fun LoginView(
    onNavigateToRegister:() -> Unit,
    onLoginSuccess: () -> Unit,
){
    val authVM: AuthViewModel= viewModel()
    LoginViewContent(
        email = authVM.email,
        password = authVM.password,
        errorMessage = authVM.errorMessage,
        isLoading = authVM.isLoading,
        onEmailChange = {authVM.email=it},
        onPasswordChange = {authVM.password = it},
        onLogin = {
            authVM.login(onLoginSuccess)
        },
        onClearError = {
            authVM.clearError()
        },
        onNavigateToRegister = onNavigateToRegister
    )
}

@Composable
fun LoginViewContent(
    email: String,
    password: String,
    errorMessage: String?,
    isLoading: Boolean,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLogin: () -> Unit,
    onClearError: () -> Unit,
    onNavigateToRegister: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text="Iniciar sesión",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        spaceV(32)

        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            label = {Text("Correo electrónico")},
            leadingIcon = {
                Icon(Icons.Default.Email,
                    contentDescription = "Email icon")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        spaceV(16)

        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = {Text("Contraseña")},
            leadingIcon = {
                Icon(
                    Icons.Default.Lock,
                    contentDescription = "Password icon"
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        errorMessage?.let { error ->
            spaceV(8)
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                fontSize = 14.sp,
                modifier = Modifier.align(Alignment.Start)
            )
        }

        spaceV(24)

        Button(
            onClick = onLogin,
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        ) {
            if(isLoading){
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = Color.White,
                    strokeWidth = 2.dp
                )
            }else{
                Text("Entrar")
            }
        }
        spaceV(16)

        TextButton(
            onClick = {
                onClearError()
                onNavigateToRegister()
            }
        ) {
            Text("¿No tienes una cuenta? Crea una aquí")
        }
    }

}

@Preview (showBackground = true)
@Composable
fun LoginViewPreview(){
    AplicacionTallerTheme {
        LoginViewContent(
            email = "correo@gmail.com",
            password = "123456",
            errorMessage = null,
            isLoading = false,
            onEmailChange = {},
            onPasswordChange = {},
            onLogin = {},
            onClearError = {},
            onNavigateToRegister = {}
        )
    }
}
