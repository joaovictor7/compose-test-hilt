package com.composetest.core.designsystem.component.asyncimage

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.SubcomposeAsyncImageContent
import coil3.request.ImageRequest
import coil3.request.crossfade

@Composable
fun AsyncImage(
    modifier: Modifier = Modifier,
    loadingModifier: Modifier = Modifier,
    url: String,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.None
) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        contentDescription = null,
        alignment = alignment,
        loading = {
            CircularProgressIndicator(modifier = loadingModifier)
        },
        success = {
            SubcomposeAsyncImageContent(
                modifier = modifier,
                contentScale = contentScale
            )
        },
    )
}