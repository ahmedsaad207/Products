package com.example.products.data.local

import com.example.products.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductsLocalDataSource {
    suspend fun insertAllProducts(products: List<Product>)

    suspend fun getAllProducts(): Flow<List<Product>>

    suspend fun deleteProduct(product: Product): Int

    suspend fun insertProduct(product: Product): Long

}