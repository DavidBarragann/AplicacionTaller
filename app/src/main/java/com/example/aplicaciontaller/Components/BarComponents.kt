package com.example.aplicaciontaller.Components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collection.mutableVectorOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.aplicaciontaller.ui.theme.Green1
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.aplicaciontaller.R

@Composable
fun Title(text:String){
    Text(text=text, fontSize=25.sp, color = Green1, fontWeight = FontWeight.Bold)
}

@Composable
fun CartButton(){
    FloatingActionButton(
        onClick = {},
        containerColor = MaterialTheme.colorScheme.primary) {
        Icon(
            painter = painterResource(R.drawable.outline_shopping_cart_24),
            contentDescription = "Ir al carrito"
        )
    }
}
@Composable
fun MainScaffold(
    selectedItem:Int,
    onItemSelected:(Int) -> Unit,
    topBar:@Composable () -> Unit ={},
    floatingActionButton: @Composable () -> Unit  = {},
    content: @Composable (PaddingValues) -> Unit
){
    Scaffold(
        topBar=topBar,
        bottomBar = {
            BottomMenu(
                selectedItem = selectedItem,
                onItemSelected = onItemSelected
            )
        },
        floatingActionButton = floatingActionButton,
        content = content
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarComponent(){
    //Almacenar texto escrito
    var query by remember { mutableStateOf("") }
    //Indicar si la barra de busqueda está activa
    var activa by remember { mutableStateOf(false) }
    SearchBar(
        inputField = {
            SearchBarDefaults.InputField(
                query = query,

                //Actualizar cada que el usuario escriba
                onQueryChange = {
                    query = it
                },
                //Se ejecuta cuando el usuario realiza una búsqueda
                onSearch = {
                    activa = false
                },
                expanded=activa,
                onExpandedChange = {
                    activa = it
                },
                //Texto de fondo
                placeholder = {
                    Text("Buscar productos...")
                },
                //definir icono
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Buscar"
                    )
                },
                //Icono para cerrar la búsqueda
                trailingIcon = {
                    if(activa){
                        IconButton(
                            onClick = {
                                if(query.isNotEmpty()){
                                    query=""
                                }else{
                                    activa=false
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription="Cerrar"
                            )
                        }
                    }
                }
            )
        },
        //Estado de expansión
        expanded=activa,

        onExpandedChange = {
            activa = it
        },

        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            ),
        //Forma de la barra
        shape = SearchBarDefaults.inputFieldShape,
        //Elevacion
        tonalElevation = SearchBarDefaults.TonalElevation,
        shadowElevation = SearchBarDefaults.ShadowElevation,

        //Contenido con la barra expandida.
        content = {
            Text(
                text = "Buscando: $query",
                modifier = Modifier.padding(16.dp),
                fontSize = 14.sp
            )
        }
    )
}