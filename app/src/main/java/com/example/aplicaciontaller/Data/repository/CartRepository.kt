package com.example.aplicaciontaller.Data.repository

import com.example.aplicaciontaller.Data.dao.CartDao
import com.example.aplicaciontaller.Data.model.CartItem
import kotlinx.coroutines.flow.Flow

class CartRepository (private val cartDao: CartDao){
    val cartItems: Flow<List<CartItem>> = cartDao.getCartItems()

    //Agregar item al carrito
    suspend fun addItem(productId: Int){
        //comparamos el producto por su id para ver si existe
        val existingItem = cartDao.getCartItemById(productId)
        if(existingItem!=null){
            cartDao.insertOrUpdate(existingItem.copy(quantity = existingItem.quantity+1))
        }else{
            cartDao.insertOrUpdate(CartItem(productId,1))
        }
    }

    //Actualizar cantidad de productos en el carrito
    suspend fun updateQuantity(productId: Int, newQuantity:Int){
        if (newQuantity <=0){
            cartDao.getCartItemById(productId)?.let { cartDao.deleteCartItem(it) }
        }else{
            cartDao.insertOrUpdate(CartItem(productId,newQuantity))
        }
    }

    //borrar elemento del carrito
    suspend fun deleteItem(productId: Int){
        cartDao.getCartItemById(productId)?.let { cartDao.deleteCartItem(it) }
    }
}