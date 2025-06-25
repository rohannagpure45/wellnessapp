package com.example.mobileapplicationdevelopment2025.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ItemCard(
    imageUrl: String,
    title: String,
    subtitle: String,
    description: String
) {
    Card(modifier = Modifier.padding(8.dp)) {
        GlideImage(
            model = imageUrl,
            contentDescription = title,
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = title)
            Text(text = subtitle)
            Text(text = description)
        }
    }
}
