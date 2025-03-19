package com.example.products

import com.example.products.model.Product

sealed class Response {
    data object Loading : Response()
    data class Success(val data: List<Product>) : Response()
    data class Failure(val error: String) : Response()
}