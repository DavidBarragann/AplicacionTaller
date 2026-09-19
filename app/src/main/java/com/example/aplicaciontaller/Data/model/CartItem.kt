package com.example.aplicaciontaller.Data.model

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "cart")
data class CartItem(
    @PrimaryKey
    val productId: Int,
    val quantity: Int
)
