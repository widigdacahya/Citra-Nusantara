package com.example.citranusantaraandroid.model

data class FeaturedEventItem(
    val id: Int,
    val title: String,
    val location : String,
    val date: String,
    val imageUrl: String,
    val description: String,
    val organizer: String,
    val websiteUrl: String? = null
)