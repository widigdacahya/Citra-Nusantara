package com.example.citranusantaraandroid.ui.components.screen

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object ArticleDetailScreen : Screen("article_detail_screen")
    object CategoryItemsScreen : Screen("category_items_screen")
}