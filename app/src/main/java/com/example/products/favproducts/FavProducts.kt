package com.example.products.favproducts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.products.ProductRow
import com.example.products.Response
import com.example.products.allproducts.LoadingIndicator
import com.example.products.model.Product

@Composable
fun FavProducts(
    viewModel: FavProductsViewModel,
    snackBarHostState: SnackbarHostState,
    titleState: MutableState<String>
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val messageState = viewModel.message.collectAsStateWithLifecycle()
    titleState.value = "Favorites"

    when (uiState) {
        is Response.Loading -> {
            LoadingIndicator()
        }

        is Response.Success -> {
            ProductsRoomList(
                products = (uiState as Response.Success).data,
                viewModel,
                snackBarHostState,
                messageState
            )
        }

        is Response.Failure -> {
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(),
                text = (uiState as Response.Failure).error,
                fontSize = 22.sp
            )
        }
    }
}

@Composable
fun ProductsRoomList(
    products: List<Product>,
    viewModel: FavProductsViewModel,
    snackBarHostState: SnackbarHostState,
    messageState: State<String?>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(products.size) { index ->
            ProductRow(
                products[index],
                "Remove from Favorites",
                snackBarHostState,
                messageState
            ) {
                viewModel.deleteProduct(products[index])
            }
        }
    }
}
