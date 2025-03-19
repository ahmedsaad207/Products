package com.example.products

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen() {
    @Serializable
    object AllProduct: Screen()

    @Serializable
    object FavProduct : Screen()
}