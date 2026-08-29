package com.example.aplicaciontaller.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.aplicaciontaller.Components.CartButton
import com.example.aplicaciontaller.Components.MainScaffold
import com.example.aplicaciontaller.Components.SearchBarComponent
import com.example.aplicaciontaller.Components.Title
import com.example.aplicaciontaller.ui.theme.AplicacionTallerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    modifier: Modifier = Modifier,
    onItemSelected: (Int) -> Unit
){
    MainScaffold(
        selectedItem = 0,
        onItemSelected = onItemSelected,
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    title = {
                        Title(text = "Home View")
                    }
                    //colors = TopAppBarDefaults.topAppBarColors(
                    //containerColor = MaterialTheme.colorScheme.Red
                )
                SearchBarComponent()
            }
        },
        floatingActionButton = {
            CartButton()
        }
    ) {padding ->
        ContentHomeView(Modifier.padding(padding))
    }
}

@Composable
fun ContentHomeView(
    modifier: Modifier = Modifier
){
    Column(
        modifier=Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text="Contenido de nuestra home view")
    }
}

@Preview(showBackground = true)
@Composable
fun HomeViewPreview(){
    AplicacionTallerTheme{
        HomeView(
            modifier = Modifier,
            onItemSelected = { }
        )
    }
}