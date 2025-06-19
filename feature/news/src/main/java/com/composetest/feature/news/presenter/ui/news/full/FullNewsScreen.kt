package com.composetest.feature.news.presenter.ui.news.full

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.composetest.core.designsystem.component.asyncimage.AsyncImage
import com.composetest.core.designsystem.component.topbar.TopBarWithoutTitle
import com.composetest.core.designsystem.dimension.Spacing
import com.composetest.core.designsystem.dimension.screenMargin
import com.composetest.core.designsystem.theme.ComposeTestTheme

@Composable
internal fun FullNewsScreen(uiState: FullNewsUiState) {
    Scaffold(topBar = { TopBarWithoutTitle() }) { paddingValues ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                uiState.imageUrl?.let {
                    AsyncImage(
                        url = it,
                        alignment = Alignment.TopCenter,
                        contentScale = ContentScale.None
                    )
                }
            }
            Column(
                modifier = Modifier.padding(screenMargin),
                verticalArrangement = Arrangement.spacedBy(Spacing.medium)
            ) {
                Text(
                    text = uiState.title,
                    style = MaterialTheme.typography.titleLarge
                )
                uiState.description?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                uiState.content?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

@Composable
@Preview
private fun Preview() {
    ComposeTestTheme {
        FullNewsScreen(
            uiState = FullNewsUiState(),
        )
    }
}