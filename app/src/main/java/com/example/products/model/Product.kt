package com.example.products.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Product(
    @PrimaryKey
    var id: String,
    var title: String? = null,
    var price: String? = null,
    var description: String? = null,
    var rating: String? = null,
    var brand: String? = null,
    var thumbnail: String? = null
)