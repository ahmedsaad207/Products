package com.example.products

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.products.model.Product
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductRow(
    product: Product,
    actionName: String,
    snackBarHostState: SnackbarHostState,
    messageState: State<String?>,
    deleteOrInsert: (product: Product) -> Unit
) {
    val scope = rememberCoroutineScope()


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(4.dp)
            .clip(RoundedCornerShape(8.dp)),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        GlideImage(
            model = product.thumbnail,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = product.title ?: "title is null",
                fontSize = 20.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth()

            )
            Text(
                text = product.brand ?: "brand is null",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth()
            )

            Text(
                text = product.description ?: "description is null",
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth()
            )

            Text(
                text = product.price ?: "price is null",
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth()
            )

            Button(onClick = {
                deleteOrInsert(product)
                Log.d("TAG", "scope.launch: ${messageState.value}")
                scope.launch {
                    delay(100)
                    snackBarHostState.showSnackbar(
                        message = messageState.value ?: "is null",
                        duration = SnackbarDuration.Short
                    )
                }
            }) {
                Text(text = actionName)
            }


        }
    }
}