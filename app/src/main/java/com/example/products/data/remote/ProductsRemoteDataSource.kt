package com.example.products.data.remote

import com.example.products.model.ProductResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ProductsRemoteDataSource {
    suspend fun getProductResponse(): Flow<Response<ProductResponse?>>
}