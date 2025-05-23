package com.example.citranusantaraandroid.ui.components.screen


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.citranusantaraandroid.data.local.CategoryItemData
import com.example.citranusantaraandroid.data.local.FeaturedEventData
import com.example.citranusantaraandroid.model.CategoryItem
import com.example.citranusantaraandroid.model.FeaturedEventItem
import com.example.citranusantaraandroid.ui.components.CategoryCard
import com.example.citranusantaraandroid.ui.components.FeaturedEventItemCard

@Composable
fun HomeScreen() {

    val featuredEventItem = FeaturedEventData.items
    val categoryItem = CategoryItemData.items

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            HeaderSection(modifier = Modifier.padding(horizontal = 16.dp))
            Spacer(modifier = Modifier.height(20.dp))
            FeaturedEventCarousel(
                items = featuredEventItem,
                onFeaturedItemClick = { clickItemId ->
                    println("eheheh ke click Item Featured Event Id: $clickItemId")
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            CategoryGrid(
                items = categoryItem,
                modifier = Modifier.padding(horizontal = 16.dp),
                onCategoryItemClick = { categoryId ->
                    println("eheheh ke click Item Category Id: $categoryId")
                }
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }

}



@Composable
fun HeaderSection(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "Citra Nusantara",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Wajah Indonesia dalam setiap Kisah",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun FeaturedEventCarousel(
    items: List<FeaturedEventItem>,
    onFeaturedItemClick: (itemId: Int) -> Unit
) {


    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(items, key = { it.id }) { itemData ->
            FeaturedEventItemCard(
                featuredEventItem = itemData,
                onClick = { onFeaturedItemClick(itemData.id) }
            )
        }
    }
}

@Composable
fun CategoryGrid(
    modifier : Modifier = Modifier,
    items: List<CategoryItem>,
    onCategoryItemClick: (categoryId: Int) -> Unit
) {
    val rowsOfItems = items.chunked(2)

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        rowsOfItems.forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    CategoryCard(
                        item = rowItems[0],
                        onClick = {onCategoryItemClick(rowItems[0].id)}
                    )
                }

                if (rowItems.size > 1) {
                    Box(modifier = Modifier.weight(1f)) {
                        CategoryCard(
                            item = rowItems[1],
                            onClick = {onCategoryItemClick(rowItems[1].id)}
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}