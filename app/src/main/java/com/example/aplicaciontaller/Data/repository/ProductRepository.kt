package com.example.aplicaciontaller.Data.repository

import com.example.aplicaciontaller.Data.dao.ProductDao
import com.example.aplicaciontaller.Data.model.Product
import kotlinx.coroutines.flow.Flow

class ProductRepository(private val productDao: ProductDao) {
    //Obtener todos los productos
    val allProducts: Flow<List<Product>> = productDao.getAllProduct()

    //Insertar nuevos products
    suspend fun insertProduct(product: Product){
        productDao.insertProduct(product)
    }

    //Obtener la información de los productos
    suspend fun getProductById(id:Int)=productDao.getProductById(id)

    //Conteo total de productos
    suspend fun getProductCount()=productDao.getProductCount()
}