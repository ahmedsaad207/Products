package com.example.products.data.remote

import com.example.products.model.ProductResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import retrofit2.Response

class ProductsRemoteDataSourceImpl(private val service: ProductService) : ProductsRemoteDataSource {
    override suspend fun getProductResponse(): Flow<Response<ProductResponse?>> {
        return flowOf(service.getProductResponse())
    }
}