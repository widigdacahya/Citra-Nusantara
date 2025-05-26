package com.example.citranusantaraandroid.ui.components.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.citranusantaraandroid.R
import com.example.citranusantaraandroid.data.local.ArticleData


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailScreen(navController: NavController, articleId: Int?) {
    val article = ArticleData.items.find { it.id == articleId }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                       "Detail Article",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
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
        if (article!= null) {
           Column(
               modifier = Modifier
                   .fillMaxSize()
                   .padding(paddingValues)
                   .verticalScroll(rememberScrollState())
                   .padding(16.dp)
           ) {
               AsyncImage(
                   model = ImageRequest.Builder(LocalContext.current)
                       .data(article.imageUrl)
                       .crossfade(true)
                       .build(),
                   placeholder = painterResource(R.drawable.icons8_photo_gallery),
                   contentDescription = "Image of ${article.title}",
                   modifier = Modifier
                       .fillMaxWidth()
                       .height(220.dp)
                       .clip(MaterialTheme.shapes.medium),
                   contentScale = ContentScale.Crop
               )

               Spacer(modifier = Modifier.height(16.dp))

               Text(
                   text = article.title,
                   style = MaterialTheme.typography.headlineMedium,
                   fontWeight = FontWeight.Bold
               )

               Spacer(modifier = Modifier.height(8.dp))

               Row(
                   verticalAlignment = Alignment.CenterVertically,
                   modifier = Modifier.fillMaxWidth()
               ) {
                   AsyncImage(
                       model = ImageRequest.Builder(LocalContext.current)
                           .data(article.authorImageUrl)
                           .crossfade(true)
                           .build(),
                       placeholder = painterResource(R.drawable.icons8_user),
                       contentDescription = "Image of ${article.author}",
                       modifier = Modifier
                           .size(28.dp)
                           .clip(MaterialTheme.shapes.medium),
                       contentScale = ContentScale.Crop
                   )

                   Spacer(modifier = Modifier.width(12.dp))

                   Column {
                       Text(
                           text = article.author,
                           style = MaterialTheme.typography.labelMedium,
                           fontWeight = FontWeight.Medium
                       )

                       Text(
                           text = article.date,
                           style = MaterialTheme.typography.bodySmall,
                           color = MaterialTheme.colorScheme.onSurfaceVariant
                       )
                   }
               }

               Spacer(modifier = Modifier.height(12.dp))

               Text(
                   text = article.description,
                   style = MaterialTheme.typography.bodyMedium,
                   textAlign = TextAlign.Justify
               )

               Spacer(modifier = Modifier.height(16.dp))
           }
        } else {
            Box(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Article not found")
            }
        }
    }
}