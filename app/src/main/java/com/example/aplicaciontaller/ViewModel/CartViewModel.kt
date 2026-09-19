package com.example.aplicaciontaller.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicaciontaller.Data.model.CartProduct
import com.example.aplicaciontaller.Data.repository.CartRepository
import com.example.aplicaciontaller.Data.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CartViewModel(private val cartRepository: CartRepository, private val productRepository: ProductRepository):
    ViewModel() {
    val cartProducts: StateFlow<List<CartProduct>> = cartRepository.cartItems
        .map { items ->
            items.mapNotNull{
                item -> productRepository.getProductById(item.productId)?.let { product ->
                CartProduct(productId = product, quantity = item.quantity)
            }
            }

        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000),emptyList())
    fun addToCart(productId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            cartRepository.addItem(productId)
        }
    }

    fun updateQuantity(productId: Int,newQuantity: Int){
        viewModelScope.launch {
            cartRepository.updateQuantity(productId,newQuantity)
        }
    }
    fun removeFromCart(productId: Int){
        viewModelScope.launch {
            cartRepository.deleteItem(productId)
        }
    }
}