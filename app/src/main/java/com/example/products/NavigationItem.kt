package com.example.products

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    var label: String,
    var icon: ImageVector,
    val route: Screen
)