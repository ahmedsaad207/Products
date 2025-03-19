package com.example.products.allproducts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
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
import com.example.products.model.Product

@Composable
fun AllProducts(
    viewModel: AllProductsViewModel,
    snackBarHostState: SnackbarHostState,
    titleState: MutableState<String>
) {
    val uiState by viewModel.response.collectAsStateWithLifecycle()
    val messageState = viewModel.message.collectAsStateWithLifecycle()
    titleState.value = "Home"

    when (uiState) {
        is Response.Loading -> {
            LoadingIndicator()
        }

        is Response.Success -> {
            ProductsList(
                data = (uiState as Response.Success).data,
                viewModel,
                snackBarHostState,
                messageState
            )
        }

        is Response.Failure -> {
            Text(
                modifier = Modifier.fillMaxSize().wrapContentSize(),
                text = (uiState as Response.Failure).error,
                fontSize = 22.sp
            )
        }
    }
}

@Composable
fun ProductsList(
    data: List<Product>,
    viewModel: AllProductsViewModel,
    snackBarHostState: SnackbarHostState,
    messageState: State<String>,

    ) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(data.size) { index ->
            ProductRow(
                data[index],
                "Add to Favorites",
                snackBarHostState,
                messageState
            ) {
                viewModel.insertProduct(data[index])
            }
        }
    }
}

@Composable
fun LoadingIndicator() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()
    ) { CircularProgressIndicator() }
}
