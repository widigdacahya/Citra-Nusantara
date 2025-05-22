package com.example.citranusantaraandroid.data.local

import androidx.compose.ui.graphics.Color
import com.example.citranusantaraandroid.model.CategoryItem

object CategoryItemData {
    val items = listOf(
        CategoryItem(1, "Tari Tradisional", "Ragam Tarian Nusantara", Color(0xFFE0D6FF)), // Ungu muda
        CategoryItem(2, "Kuliner", "Cita Rasa Indonesia", Color(0xFFFFE0B2)), // Oranye muda
        CategoryItem(3, "Cerita Rakyat", "Kisah Leluhur", Color(0xFFC8E6C9)), // Hijau muda
        CategoryItem(4, "Benda Khas Daerah", "Kerajinan & Seni", Color(0xFFBBDEFB)), // Biru muda
        CategoryItem(5, "Tempat Bersejarah", "Situs dengan Jejak Sejarah", Color(0xFFFFCDD2)), // Merah muda
        CategoryItem(6, "Soon", "More Culture Soon..", Color(0xFFB2DFDB)) // Teal muda
    )
}
