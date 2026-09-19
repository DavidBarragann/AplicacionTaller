package com.example.aplicaciontaller.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicaciontaller.Data.model.Category
import com.example.aplicaciontaller.Data.repository.CategoryRepository
import com.example.aplicaciontaller.ui.theme.ultra_l_blue
import com.example.aplicaciontaller.ui.theme.ultra_l_green
import com.example.aplicaciontaller.ui.theme.ultra_l_purple
import com.example.aplicaciontaller.ui.theme.ultra_l_red
import com.example.aplicaciontaller.ui.theme.ultra_l_yellow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.aplicaciontaller.R

class HomeViewModel (private val categoryRepository: CategoryRepository): ViewModel(){
    private val _categories= MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories
    init {
        viewModelScope.launch {
            insertInitialCategories()
            loadCategories()
        }
    }
    private suspend fun loadCategories(){
        _categories.value = categoryRepository.getCategories()
    }
    private suspend fun insertInitialCategories(){
        if (categoryRepository.countCategories()==0){
            val categories = listOf(
                Category(
                    name = "Frutas",
                    image = R.drawable.manzana,
                    color = ultra_l_green
                ),
                Category(
                    name = "Carnes",
                    image = R.drawable.carne,
                    color = ultra_l_red
                ),
                Category(
                    name = "Lácteos",
                    image = R.drawable.leche,
                    color = ultra_l_blue
                ),
                Category(
                    name = "Panadería",
                    image = R.drawable.pan,
                    color = ultra_l_yellow
                ),
                Category(
                    name = "Bebidas",
                    image = R.drawable.bebidas,
                    color = ultra_l_purple
                )
            )
            categoryRepository.insertCategories(categories)
        }
    }
}