package com.example.aplicaciontaller.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aplicaciontaller.Data.repository.CartRepository
import com.example.aplicaciontaller.Data.repository.ProductRepository

class CartViewModelFactory(private val cartRepository: CartRepository,
    private val productRepository: ProductRepository) : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>):T{
        if (modelClass.isAssignableFrom(CartViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return CartViewModel(cartRepository,productRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}