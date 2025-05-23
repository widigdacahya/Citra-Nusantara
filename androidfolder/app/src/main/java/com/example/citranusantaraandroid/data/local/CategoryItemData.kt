package com.example.citranusantaraandroid.data.local

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.EmojiPeople
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.ui.graphics.Color
import com.example.citranusantaraandroid.model.CategoryItem


object CategoryItemData {
    val items = listOf(
        CategoryItem(1, "Tari Tradisional", "Ragam Tarian Nusantara", Color(0xFF8B5CF6), Icons.Filled.EmojiPeople), // light purple
        CategoryItem(2, "Kuliner", "Cita Rasa Indonesia", Color(0xFFF97316), Icons.Filled.Restaurant), // Oranye muda
        CategoryItem(3, "Cerita Rakyat", "Kisah Leluhur", Color(0xFF22C55E), Icons.Filled.ReceiptLong), // Hijau muda
        CategoryItem(4, "Benda Khas Daerah", "Kerajinan & Seni", Color(0xFF3B82F6), Icons.Filled.Palette), // Biru muda
        CategoryItem(5, "Tempat Bersejarah", "Situs dengan Jejak Sejarah", Color(0xFFEF4444), Icons.Filled.AccountBalance), // Merah muda
        CategoryItem(6, "Soon", "More Culture Soon..", Color(0xFF14B8A6), Icons.Filled.MoreHoriz) // Teal muda
    )
}
