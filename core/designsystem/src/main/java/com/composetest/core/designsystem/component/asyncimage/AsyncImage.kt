package com.composetest.core.designsystem.component.asyncimage

import androidx.compose.foundation.Image
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.SubcomposeAsyncImageContent
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.composetest.core.designsystem.R
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.designsystem.util.isPreview

@Composable
fun AsyncImage(
    modifier: Modifier = Modifier,
    loadingModifier: Modifier = Modifier,
    url: String,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.None
) {
    if (isPreview) {
        PlaceholderImage(modifier = modifier, contentScale = contentScale)
        return
    }
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

@Composable
private fun PlaceholderImage(modifier: Modifier, contentScale: ContentScale) {
    Image(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_refresh),
        contentDescription = null,
        colorFilter = ColorFilter.tint(LocalContentColor.current),
        contentScale = contentScale,
    )
}

@Composable
@Preview
private fun Preview() {
    ComposeTestTheme {
        AsyncImage(url = "https://url-fake.com")
    }
}