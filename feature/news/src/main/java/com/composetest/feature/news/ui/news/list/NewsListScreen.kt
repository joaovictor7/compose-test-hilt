package com.composetest.feature.news.ui.news.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.composetest.core.designsystem.components.asyncimage.AsyncImage
import com.composetest.core.designsystem.components.buttons.TryAgainButton
import com.composetest.core.designsystem.components.pulltorefresh.PullToRefresh
import com.composetest.core.designsystem.components.shimmer.Shimmer
import com.composetest.core.designsystem.constants.screenMargin
import com.composetest.core.designsystem.constants.topScreenMarginList
import com.composetest.core.designsystem.dimensions.Spacing
import com.composetest.core.designsystem.extensions.horizontalScreenMargin
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.designsystem.utils.getSharedShimmerOffset
import com.composetest.core.domain.models.news.ArticleModel
import com.composetest.core.router.extensions.navigateTo
import com.composetest.core.ui.interfaces.Command
import com.composetest.core.ui.utils.UiEventsObserver
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import java.time.LocalDateTime

@Composable
internal fun NewsListScreen(
    uiState: NewsListUiState,
    uiEvent: Flow<NewsListUiEvent> = emptyFlow(),
    onExecuteCommand: (Command<NewsListCommandReceiver>) -> Unit = {},
    navController: NavHostController = rememberNavController(),
) {
    val shimmerOffset by getSharedShimmerOffset()
    UiEventsHandler(uiEvent = uiEvent, navController = navController)
    PullToRefresh(
        isRefreshing = uiState.isLoading,
        onRefresh = { onExecuteCommand(NewsListCommand.Refresh) }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .horizontalScreenMargin(),
            contentPadding = topScreenMarginList,
            verticalArrangement = Arrangement.spacedBy(Spacing.twentyEight)
        ) {
            if (uiState.isLoading) {
                items(4) {
                    ListItemShimmer(shimmerOffset = shimmerOffset)
                }
            } else {
                items(uiState.articles) {
                    NewsCard(articleModel = it, onExecuteCommand = onExecuteCommand)
                }
                item {
                    Spacer(Modifier.windowInsetsPadding(WindowInsets.navigationBars))
                }
            }
        }
        if (uiState.showRetryButton) {
            RetryButton(onExecuteCommand = onExecuteCommand)
        }
    }
}

@Composable
private fun NewsCard(
    articleModel: ArticleModel,
    onExecuteCommand: (Command<NewsListCommandReceiver>) -> Unit
) {
    ElevatedCard(onClick = { onExecuteCommand(NewsListCommand.NavigateToFullNews(articleModel)) }) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            articleModel.urlToImage?.let {
                AsyncImage(
                    modifier = Modifier.sizeIn(maxHeight = 180.dp),
                    loadingModifier = Modifier.padding(vertical = Spacing.twentyEight),
                    url = it,
                    alignment = Alignment.TopCenter,
                    contentScale = ContentScale.None
                )
            }
        }
        Column(
            modifier = Modifier.padding(screenMargin),
            verticalArrangement = Arrangement.spacedBy(Spacing.twelve)
        ) {
            Text(
                text = articleModel.provider,
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = articleModel.title,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
private fun RetryButton(onExecuteCommand: (Command<NewsListCommandReceiver>) -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        TryAgainButton(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.surface)
                .fillMaxSize()
        ) { onExecuteCommand(NewsListCommand.Refresh) }
    }
}

@Composable
private fun ListItemShimmer(shimmerOffset: Float) {
    Shimmer(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
        offset = shimmerOffset,
    )
}

@Composable
private fun UiEventsHandler(
    uiEvent: Flow<NewsListUiEvent>,
    navController: NavHostController
) {
    UiEventsObserver(uiEvent) {
        when (it) {
            is NewsListUiEvent.NavigateTo -> navController.navigateTo(it.navigationModel)
        }
    }
}

@Preview
@Composable
private fun Preview() {
    ComposeTestTheme {
        NewsListScreen(
            uiState = NewsListUiState(
                showRetryButton = true,
                isLoading = true,
                articles = listOf(
                    ArticleModel(
                        provider = "Teste",
                        title = "Teste",
                        description = "Teste",
                        urlToImage = "Teste",
                        publishedAt = LocalDateTime.now(),
                        content = "Teste"
                    )
                )
            ),
        )
    }
}