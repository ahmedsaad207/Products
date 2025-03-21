package com.example.products.data.remote

import com.example.products.model.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductService {
    @GET("products")
    suspend fun getProductResponse(): Response<ProductResponse?>
}