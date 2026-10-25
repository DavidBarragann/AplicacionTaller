package com.example.aplicaciontaller.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aplicaciontaller.Data.repository.CartRepository
import com.example.aplicaciontaller.Data.repository.ProductRepository

class CkecoutViewModelFactory(
    private val cartRepository: CartRepository,
    private val productRepository: ProductRepository
) : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T{
        if(modelClass.isAssignableFrom(CheckOutViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return CheckOutViewModel(cartRepository,productRepository)as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}