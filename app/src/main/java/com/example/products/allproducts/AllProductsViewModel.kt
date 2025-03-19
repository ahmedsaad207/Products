package com.example.products.allproducts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.products.Response
import com.example.products.data.repo.ProductRepository
import com.example.products.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class AllProductsViewModel(private val repo: ProductRepository) : ViewModel() {
    private val _response = MutableStateFlow<Response>(Response.Loading)
    val response = _response.asStateFlow()

    private val _message = MutableStateFlow("")
    val message = _message.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val response = repo.getProductResponse()
                response
                    .catch { e -> _response.emit(Response.Failure(e.message ?: "Error From Api")) }
                    .collect {
                        val products = it.body()?.products
                        _response.emit(Response.Success(products ?: arrayListOf()))
                    }
            } catch (e: Exception) {
                _response.emit(Response.Failure(e.message ?: "Error From Api"))
            }

        }
    }

//    fun getAllProducts(): LiveData<List<Product>?> = response

    fun insertProduct(product: Product) {
        viewModelScope.launch {
            try {
                val isInserted = repo.insertProduct(product)
                _message.emit(if (isInserted == -1L) "Already Added to Favorites" else "Added to Favorites")
                Log.i("TAG", "_message.postValue: ")
            } catch (e: Exception) {
                _message.emit("Couldn't Insert Product${e.message}")
            }
        }
    }
}