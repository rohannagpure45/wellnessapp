package com.example.mobileapplicationdevelopment2025.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.mobileapplicationdevelopment2025.data.WellnessItem

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(item: WellnessItem) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(item.name, style = MaterialTheme.typography.h4)
        Spacer(Modifier.height(8.dp))
        Text("Category: ${item.category}")
        item.expiryDate?.let { Text("Expiry Date: $it") }
        item.warranty?.let { Text("Warranty: $it years") }
        Text("Price: $${item.price}")
        Spacer(Modifier.height(16.dp))
        GlideImage(
            model = item.imageUrl,
            contentDescription = item.name,
            modifier = Modifier.fillMaxWidth().height(200.dp),
            contentScale = ContentScale.Crop
        )
    }
}
