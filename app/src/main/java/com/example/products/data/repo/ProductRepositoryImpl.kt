package com.example.products.data.repo

import com.example.products.data.local.ProductsLocalDataSource
import com.example.products.data.remote.ProductsRemoteDataSource
import com.example.products.model.Product
import com.example.products.model.ProductResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class ProductRepositoryImpl(
    private val localDataSource: ProductsLocalDataSource,
    private val remoteDataSource: ProductsRemoteDataSource
) : ProductRepository {
    override suspend fun getProductResponse(): Flow<Response<ProductResponse?>> {
        return remoteDataSource.getProductResponse()
    }

    override suspend fun insertAllProducts(products: List<Product>) {
        localDataSource.insertAllProducts(products)
    }

    override suspend fun getAllProducts(): Flow<List<Product>> {
        return localDataSource.getAllProducts()
    }

    override suspend fun deleteProduct(product: Product): Int {
        return localDataSource.deleteProduct(product)
    }

    override suspend fun insertProduct(product: Product): Long {
        return localDataSource.insertProduct(product)
    }

}