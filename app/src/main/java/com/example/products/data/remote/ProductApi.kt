package com.example.products.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ProductApi {
    private const val BASE_URL = "https://dummyjson.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: ProductService = retrofit.create(ProductService::class.java)
}