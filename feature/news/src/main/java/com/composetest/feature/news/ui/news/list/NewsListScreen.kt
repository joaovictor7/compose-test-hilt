package com.composetest.feature.news.ui.news.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.composetest.core.designsystem.components.asyncimage.AsyncImage
import com.composetest.core.designsystem.components.dialogs.SimpleDialog
import com.composetest.core.designsystem.constants.screenMargin
import com.composetest.core.designsystem.constants.topScreenMarginList
import com.composetest.core.designsystem.dimensions.Spacing
import com.composetest.core.designsystem.extensions.horizontalScreenMargin
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.domain.models.ArticleModel
import com.composetest.core.ui.interfaces.Command
import com.composetest.core.ui.interfaces.Screen
import com.composetest.feature.news.R
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

internal object NewsListScreen : Screen<NewsListUiState, NewsListUiEvent, NewsListCommandReceiver> {

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    override operator fun invoke(
        uiState: NewsListUiState,
        uiEvent: Flow<NewsListUiEvent>?,
        onExecuteCommand: (Command<NewsListCommandReceiver>) -> Unit
    ) {
        if (!uiState.showScreen) return
        if (uiState.showWithoutNewsMsg) {
            WithoutNews()
        }
        val pullToRefreshState = rememberPullToRefreshState()
        PullToRefreshBox(
            modifier = Modifier.fillMaxSize(),
            state = pullToRefreshState,
            isRefreshing = uiState.isLoading,
            indicator = {
                Indicator(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .windowInsetsPadding(WindowInsets.statusBars),
                    isRefreshing = uiState.isLoading,
                    state = pullToRefreshState
                )
            },
            onRefresh = { onExecuteCommand(NewsListCommand.Refresh) }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .horizontalScreenMargin(),
                contentPadding = topScreenMarginList,
                verticalArrangement = Arrangement.spacedBy(Spacing.twentyEight)
            ) {
                items(uiState.articles) {
                    NewsCard(articleModel = it, onExecuteCommand = onExecuteCommand)
                }
                item {
                    Spacer(modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars))
                }
            }
        }
        DialogsHandler(uiState = uiState, onExecuteCommand = onExecuteCommand)
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
private fun DialogsHandler(
    uiState: NewsListUiState,
    onExecuteCommand: (Command<NewsListCommandReceiver>) -> Unit
) = uiState.simpleDialogParam?.let {
    SimpleDialog(param = it) {
        onExecuteCommand(NewsListCommand.DismissSimpleDialog)
    }
}

@Composable
private fun WithoutNews() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier
                .alpha(0.6f)
                .align(Alignment.Center),
            text = stringResource(R.string.without_news_msg),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun Preview() {
    ComposeTestTheme {
        NewsListScreen(
            uiState = NewsListUiState(
                showScreen = true,
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
            uiEvent = null
        ) { }
    }
}