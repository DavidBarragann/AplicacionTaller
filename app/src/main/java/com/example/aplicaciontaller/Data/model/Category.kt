package com.example.aplicaciontaller.Data.model

import androidx.compose.ui.graphics.Color
import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "categories")
data class Category(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val name: String,
    val image: Int,
    val color: Color
)
