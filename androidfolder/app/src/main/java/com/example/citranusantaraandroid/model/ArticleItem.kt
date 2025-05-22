package com.example.citranusantaraandroid.model

data class ArticleItem(
    val id: Int,
    val author: String,
    val title: String,
    val description: String,
    val date: String,
    val imageUrl: String,
    val authorImageUrl: String? = null
)