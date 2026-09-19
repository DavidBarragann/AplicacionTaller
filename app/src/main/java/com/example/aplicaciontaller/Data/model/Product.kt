package com.example.aplicaciontaller.Data.model

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "products")
data class Product (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val name: String,
    val image : Int,
    val price : Float,
    val measure: String, //pzas, kg, und
    val isOffer: Boolean
)

