package com.example.aplicaciontaller.Data.dao

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import com.example.aplicaciontaller.Data.model.CartItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Query("SELECT * FROM cart")
    fun getCartItems(): Flow<List<CartItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(cartItem: CartItem)

    @Query("SELECT * FROM cart WHERE productId = :productId")
    suspend fun getCartItemById(productId: Int): CartItem?

    @Delete
    suspend fun deleteCartItem(cartItem: CartItem)

    @Query("DELETE FROM cart")
    suspend fun clearCart()
}