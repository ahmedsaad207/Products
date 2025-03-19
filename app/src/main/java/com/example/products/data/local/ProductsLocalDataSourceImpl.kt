package com.example.products.data.local

import com.example.products.model.Product
import kotlinx.coroutines.flow.Flow

class ProductsLocalDataSourceImpl(private val dao: ProductDao) : ProductsLocalDataSource {

    override suspend fun insertAllProducts(products: List<Product>) {
        dao.insertAllProducts(products)
    }

    override suspend fun getAllProducts(): Flow<List<Product>> {
        return dao.getAllProducts()
    }

    override suspend fun deleteProduct(product: Product): Int {
        return dao.deleteProduct(product)
    }

    override suspend fun insertProduct(product: Product): Long {
        return dao.insertProduct(product)
    }
}