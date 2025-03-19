package com.example.products.favproducts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.products.allproducts.AllProductsViewModel
import com.example.products.data.repo.ProductRepository

class FavProductsViewModelFactory(private val rep: ProductRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavProductsViewModel(rep) as T
    }
}