package com.example.aplicaciontaller.Data.database

import android.content.Context
import android.util.Log
import androidx.room3.Database
import androidx.room3.Room
import androidx.room3.RoomDatabase
import com.example.aplicaciontaller.Data.dao.CartDao
import com.example.aplicaciontaller.Data.dao.CategoryDao
import com.example.aplicaciontaller.Data.dao.ProductDao
import com.example.aplicaciontaller.Data.model.CartItem
import com.example.aplicaciontaller.Data.model.Category
import com.example.aplicaciontaller.Data.model.Product

@Database(
    entities = [
        Category::class,
        Product::class,
        CartItem::class
    ],
    version = 2,
    exportSchema = false)
abstract class AppDatabase(): RoomDatabase(){
    abstract fun categoryDao(): CategoryDao
    abstract fun productDao(): ProductDao
    abstract fun cartItemDao(): CartDao


    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase{
            return INSTANCE ?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration(true)
                    .build()
                    .also {
                        INSTANCE = it
                        Log.d("DATABASE", "DB creada: $it")
                    }
            }
        }
    }
}
