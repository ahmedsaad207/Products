package com.example.products.allproducts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.products.data.repo.ProductRepository

class AllProductsViewModelFactory(private val rep: ProductRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AllProductsViewModel(rep) as T
    }
}