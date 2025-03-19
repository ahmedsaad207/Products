package com.example.products

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.products.allproducts.AllProducts
import com.example.products.allproducts.AllProductsViewModelFactory
import com.example.products.data.local.ProductDatabase
import com.example.products.data.local.ProductsLocalDataSourceImpl
import com.example.products.data.remote.ProductApi
import com.example.products.data.remote.ProductsRemoteDataSourceImpl
import com.example.products.data.repo.ProductRepositoryImpl
import com.example.products.favproducts.FavProducts
import com.example.products.favproducts.FavProductsViewModelFactory

@Composable
fun SetupNavHost(
    navController: NavHostController,
    snackBarHostState: SnackbarHostState,
    titleState: MutableState<String>
) {
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = Screen.AllProduct
    ) {
        composable<Screen.AllProduct> {
            AllProducts(
                viewModel(
                    factory = AllProductsViewModelFactory(
                        ProductRepositoryImpl(
                            ProductsLocalDataSourceImpl(
                                ProductDatabase.getInstance(context).getProductDao()
                            ),
                            ProductsRemoteDataSourceImpl(ProductApi.service)
                        )
                    )
                ),snackBarHostState,titleState
            )
        }

        composable<Screen.FavProduct> {
            FavProducts(
                viewModel(
                    factory = FavProductsViewModelFactory(
                        ProductRepositoryImpl(
                            ProductsLocalDataSourceImpl(
                                ProductDatabase.getInstance(context).getProductDao()
                            ),
                            ProductsRemoteDataSourceImpl(ProductApi.service)
                        )
                    )
                ),snackBarHostState,titleState
            )
        }


    }
}