package com.example.aplicaciontaller.Components

import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aplicaciontaller.Data.model.CartProduct
import com.example.aplicaciontaller.Data.model.Product
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.aplicaciontaller.Data.model.CartItem

@Composable
fun ProductCardHome(
    product: Product,
    onAddClick:() -> Unit
){
    Card(modifier = Modifier.width(180.dp)
        .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ){
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = product.image),
                contentDescription = product.name,
                modifier = Modifier.size(100.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop)
            spaceV(8)
            Text(
                text = product.name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.Start)
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(style= SpanStyle(color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)){
                        append("$${String.format("%.2f",product.price)}")
                    }
                    withStyle(style = SpanStyle(color = Color.Gray)){
                        append("/${product.measure}")
                    }
                },
                fontSize = 14.sp,
                modifier = Modifier.align(Alignment.Start)
            )
            spaceV(8)
            Button(
                onClick = onAddClick,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Agregar")
            }
        }
    }
}

@Composable
fun CartProductCard(
    cartProduct: CartProduct,
    onIncrese:() -> Unit,
    onDelete: () -> Unit
){
    var showDialog by remember { mutableStateOf(false) }

    if(showDialog){
        AlertDialog(
            onDismissRequest = {showDialog=false},
            title = {Text("Eliminar producto")},
            text = {Text("¿Estás seguro de eliminar los productos?")},
            confirmButton = {
                TextButton(onClick = {
                    showDialog=false
                    onDelete()
                }) {
                    Text("Eliminar", color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(onClick = {showDialog=false}) {
                    Text("Cancelar")
                }
        }
        )
    }
    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ){
        Row(
            modifier = Modifier.padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id=cartProduct.productId.image),
                contentDescription = cartProduct.productId.name,
                modifier = Modifier.size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            spaceH(12)
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = cartProduct.productId.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = "$${String.format("%.2f",cartProduct.productId.price)}",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = "x${cartProduct.quantity}",
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            IconButton(onClick = onIncrese) {
                Icon(Icons.Default.Add, contentDescription = "Aumentar")
            }
            IconButton(onClick = {showDialog=true}) {
                Icon(Icons.Default.Delete, contentDescription = "Eliminar producto")
            }
        }
    }
}