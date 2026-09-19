package com.example.aplicaciontaller.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicaciontaller.Data.model.Product
import com.example.aplicaciontaller.Data.repository.ProductRepository
import com.example.aplicaciontaller.R
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductRepository): ViewModel() {
    //Obtener todos los productos
    val products: StateFlow<List<Product>> = repository.allProducts
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000),emptyList())
    init {
        checkAndInsertInitialData()
    }

    private fun checkAndInsertInitialData() {
        viewModelScope.launch {
            //verificar los datos iniciales, para que en caso de no tener productos, se inserten
            if(repository.getProductCount()==0){
                val initialProducts = listOf(
                    Product(name = "Pechuga de Pollo", image = R.drawable.pechuga, price = 85.0f, measure = "kg", isOffer = true),
                    Product(name = "Leche Entera", image = R.drawable.leche2, price = 25.5f, measure = "und", isOffer = false),
                    Product(name = "Aguacate Hass", image = R.drawable.aguacate, price = 60.0f, measure = "kg", isOffer = true),
                    Product(name = "Galletas Marías", image = R.drawable.galletas, price = 15.0f, measure = "pza", isOffer = false)
                )
                initialProducts.forEach { repository.insertProduct(it) }
            }
        }
    }
}