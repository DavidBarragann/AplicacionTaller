package com.example.aplicaciontaller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.aplicaciontaller.View.ContadorScreen
import com.example.aplicaciontaller.View.HomeView
import com.example.aplicaciontaller.ViewModel.ContadorViewModel
import com.example.aplicaciontaller.ui.theme.AplicacionTallerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AplicacionTallerTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    HomeView(modifier = Modifier.padding(innerPadding),
                        onItemSelected = {})
                }
            }
        }
    }
}

