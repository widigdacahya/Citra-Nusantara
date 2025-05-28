package com.example.citranusantaraandroid.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.citranusantaraandroid.viewmodel.CategoryItemsViewModel

class CategoryItemsViewModelFactory(
    private val categoryEndPointPath : String,
    private val categoryTitleFromArgs: String
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryItemsViewModel::class.java)) {
            return CategoryItemsViewModel(categoryEndPointPath, categoryTitleFromArgs) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}