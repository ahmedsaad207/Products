package com.example.products.data.repo

import com.example.products.model.Product
import com.example.products.model.ProductResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ProductRepository {
    suspend fun getProductResponse(): Flow<Response<ProductResponse?>>

    suspend fun insertAllProducts(products: List<Product>)

    suspend fun getAllProducts(): Flow<List<Product>>

    suspend fun deleteProduct(product: Product): Int

    suspend fun insertProduct(product: Product): Long

}