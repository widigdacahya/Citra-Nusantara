package com.example.citranusantaraandroid.ui.components.screen


import android.content.Intent
import android.net.Uri
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.citranusantaraandroid.data.local.ArticleData
import com.example.citranusantaraandroid.data.local.CategoryItemData
import com.example.citranusantaraandroid.data.local.FeaturedEventData
import com.example.citranusantaraandroid.model.ArticleItem
import com.example.citranusantaraandroid.model.CategoryItem
import com.example.citranusantaraandroid.model.FeaturedEventItem
import com.example.citranusantaraandroid.ui.components.CategoryCard
import com.example.citranusantaraandroid.ui.components.FeaturedEventItemCard
import androidx.core.net.toUri
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {

    val featuredEventItem = FeaturedEventData.items
    val categoryItem = CategoryItemData.items
    val articleItem = ArticleData.items

    val context = LocalContext.current

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
                onFeaturedItemClick = { websiteURL ->
                    if(!websiteURL.isNullOrBlank()) {
                        val intent = Intent(Intent.ACTION_VIEW, websiteURL.toUri())
                        try {
                            context.startActivity(intent)
                        } catch (e: Exception) {
                            val errorMessage = "Failed link ${e.localizedMessage}"
                            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                        }
                    } else {
                        Toast.makeText(context, "No attached link", Toast.LENGTH_SHORT).show()
                    }
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            CategoryGrid(
                items = categoryItem,
                modifier = Modifier.padding(horizontal = 16.dp),
                onCategoryItemClick = {

                    if (it.endpointPath.isNotBlank()) {
                        navController.navigate(Screen.CategoryItemsScreen.route + "/${it.endpointPath}/${it.title}")
                    } else {
                        // for "soon" menu
                        Toast.makeText(context, "Coming soon", Toast.LENGTH_SHORT).show()
                    }


                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            ArticleSection(
                modifier = Modifier.padding(horizontal = 16.dp),
                items = articleItem,
                onArticleItemClick = { articleId ->
                    navController.navigate(Screen.ArticleDetailScreen.route + "/$articleId")
                },
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
    onFeaturedItemClick: (websiteUrl: String?) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(items, key = { it.id }) { itemData ->
            FeaturedEventItemCard(
                featuredEventItem = itemData,
                onClick = { onFeaturedItemClick(itemData.websiteUrl) }
            )
        }
    }
}

@Composable
fun CategoryGrid(
    modifier : Modifier = Modifier,
    items: List<CategoryItem>,
    onCategoryItemClick: (categoryItem: CategoryItem) -> Unit
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
                        onClick = {onCategoryItemClick(rowItems[0])}
                    )
                }

                if (rowItems.size > 1) {
                    Box(modifier = Modifier.weight(1f)) {
                        CategoryCard(
                            item = rowItems[1],
                            onClick = {onCategoryItemClick(rowItems[1])}
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun ArticleSection(
    modifier: Modifier = Modifier,
    items: List<ArticleItem>,
    onArticleItemClick: (articleId: Int) -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "Artikel",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items.forEach { article ->
                com.example.citranusantaraandroid.ui.components.ArticleItem(
                    articleItem = article,
                    onClick = { onArticleItemClick(article.id) }
                )
            }
        }
    }
}


