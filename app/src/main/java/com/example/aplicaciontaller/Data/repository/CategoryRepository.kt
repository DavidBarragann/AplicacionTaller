package com.example.aplicaciontaller.Data.repository

import com.example.aplicaciontaller.Data.dao.CategoryDao
import com.example.aplicaciontaller.Data.model.Category

class CategoryRepository(private val categoryDao: CategoryDao) {
    suspend fun getCategories(): List<Category>{
        return categoryDao.getCategories()
    }
    suspend fun insertCategories(categories: List<Category>){
        categoryDao.insertCategories(categories)
    }
    suspend fun countCategories(): Int{
        return categoryDao.countCategories()
    }
}