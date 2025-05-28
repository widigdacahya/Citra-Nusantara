package com.example.citranusantaraandroid.ui.components.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.citranusantaraandroid.ui.components.DisplayableListItemCard
import com.example.citranusantaraandroid.viewmodel.CategoryItemsViewModel
import com.example.citranusantaraandroid.viewmodel.factory.CategoryItemsViewModelFactory


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryItemsScreen(
    navController: NavController,
    categoryEndpointPath: String,
    categoryTitle: String
) {

    val viewModel: CategoryItemsViewModel = viewModel(
        factory = CategoryItemsViewModelFactory(categoryEndpointPath, categoryTitle)
    )
    val uiState by viewModel.uiState.collectAsState()

    val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(uiState.categoryTitle.ifBlank { "Items List" }) },
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
          modifier = Modifier
              .fillMaxSize()
              .padding(paddingValues)
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (uiState.error != null) {
                Text(
                    text = "Error: ${uiState.error}",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp),
                    color = MaterialTheme.colorScheme.error
                )
            } else if (uiState.items.isEmpty()) {
                Text(
                    text = "No items found",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp)
                )
            } else {
                LazyColumn (
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ){
                    items(uiState.items, key = {it.id}) { item ->
                        DisplayableListItemCard(
                            item = item,
                            onClick = {
                                // later will navigate to detail
                                Toast.makeText(context, "Item ${item.name} - id: ${item.id} clicked", Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                }
            }
        }
    }

}