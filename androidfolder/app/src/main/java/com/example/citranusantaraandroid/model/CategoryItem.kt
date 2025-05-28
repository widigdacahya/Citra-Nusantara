package com.example.citranusantaraandroid.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class CategoryItem(
    val id: Int,
    val title: String,
    val subtitle: String,
    val color: Color,
    val iconVector: ImageVector,
    val endpointPath: String
)