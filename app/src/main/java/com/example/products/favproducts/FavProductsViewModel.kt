package com.example.products.favproducts

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.products.Response
import com.example.products.data.repo.ProductRepository
import com.example.products.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FavProductsViewModel(private val repo: ProductRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<Response>(Response.Loading)
    val uiState = _uiState.asStateFlow()

    private val _message = MutableStateFlow<String>("")
    val message: MutableStateFlow<String> = _message

    init {
        viewModelScope.launch {
            try {
                val products = repo.getAllProducts()
                products
                    .catch {
                        _uiState.emit(
                            Response.Failure(
                                it.message ?: "Error Fetch data from Room"
                            )
                        )
                    }
                    .collect {
                        _uiState.emit(Response.Success(it))
                    }
            } catch (e: Exception) {
                _uiState.emit(Response.Failure(e.message ?: "Error"))
            }

        }
    }

//    fun getAllProducts(): LiveData<List<Product>?> = products

    fun deleteProduct(product: Product) = viewModelScope.launch {
        val isDeleted = repo.deleteProduct(product)
        _message.emit(if (isDeleted == 1) "Deleted Successfully from Favorites" else "Couldn't be deleted")
//        val products = repo.getAllProducts()
//        products.collect {
//            _uiState.value = it
//        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("TAG", "onCleared: FavProductsViewModel")
    }
}