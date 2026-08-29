package com.example.aplicaciontaller.Components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BottomMenu(
    //Indica elemento seleccionado actualmente
    selectedItem : Int,
    //Recibir una función quye será ejecutada cuando el usuario seleccione una opción
    onItemSelected:(Int) -> Unit
){
    NavigationBar{
        NavigationBarItem(
            selected = selectedItem == 0,
            onClick = {
                onItemSelected(0)
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Inicio"
                )
            },
            //texto por debajo del ícono
            label = {
                Text("Inicio")
            }
        )
        NavigationBarItem(
            selected = selectedItem == 1,
            onClick = {
                onItemSelected(1)
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.LocalOffer,
                    contentDescription = "Ofertas"
                )
            },
            label = {
                Text("Ofertas")
            }
        )
        //Tercera opción: Favoritos
        NavigationBarItem(
            selected = selectedItem == 2,
            onClick = {
                onItemSelected(2)
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favoritos"
                )
            },
            label = {
                Text("Favoritos")
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.primary,
                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant
            )

        )
        //Cuarta opcion: Perfil
        NavigationBarItem(
            selected = selectedItem == 3,
            onClick = {
                onItemSelected(3)
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Perfil"
                )
            },
            label = {
                Text("Perfil")
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.primary,
                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        )
    }
}