package com.example.citranusantaraandroid.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.citranusantaraandroid.R
import com.example.citranusantaraandroid.model.ArticleItem

@Composable
fun ArticleItem(
    articleItem: ArticleItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.Top
    ) {
        Column(modifier = Modifier.weight(1f)) {

            // Writer Info (Photo + name)
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(articleItem.authorImageUrl ?: R.drawable.icons8_user)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Avatar ${articleItem.author}",
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    onError = { error ->
                        println("Error loading author avatar : ${error.result.throwable}")
                    },
                    fallback = painterResource(id = R.drawable.icons8_user),
                    placeholder = painterResource(R.drawable.icons8_user)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = articleItem.author,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.width(8.dp) )

            Text(
                text = articleItem.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = articleItem.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = articleItem.date,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.outline
            )

        }

        Spacer (modifier = Modifier.width(16.dp))

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(articleItem.imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = "Thumnail Article ${articleItem.title}",
            modifier = Modifier
                .size(width = 88.dp, height = 88.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.icons8_photo_gallery)
        )
    }
}