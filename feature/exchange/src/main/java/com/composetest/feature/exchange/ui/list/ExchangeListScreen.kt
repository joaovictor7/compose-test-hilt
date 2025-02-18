package com.composetest.feature.exchange.ui.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.composetest.core.designsystem.components.dialogs.SimpleDialog
import com.composetest.core.designsystem.components.shimmer.Shimmer
import com.composetest.core.designsystem.components.textfields.TextField
import com.composetest.core.designsystem.components.topbar.LeftTopBar
import com.composetest.core.designsystem.constants.screenMargin
import com.composetest.core.designsystem.dimensions.Spacing
import com.composetest.core.designsystem.enums.textfields.TextFieldIcon
import com.composetest.core.designsystem.extensions.horizontalScreenMargin
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.designsystem.utils.getSharedShimmerOffset
import com.composetest.core.router.extensions.navigateTo
import com.composetest.core.ui.interfaces.Command
import com.composetest.feature.exchange.R
import com.composetest.feature.exchange.models.ExchangeListRowScreenModel
import com.composetest.feature.exchange.models.ExchangeScreenModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun ExchangeListScreen(
    uiState: ExchangeListUiState,
    uiEvent: Flow<ExchangeListUiEvent> = emptyFlow(),
    onExecuteCommand: (Command<ExchangeListCommandReceiver>) -> Unit = {},
    navController: NavHostController = rememberNavController(),
) {
    val pullToRefreshState = rememberPullToRefreshState()
    val shimmerOffset by getSharedShimmerOffset()
    LaunchedEffectHandler(uiEvent = uiEvent, navController = navController)
    DialogHandler(uiState = uiState, onExecuteCommand = onExecuteCommand)
    Column(modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)) {
        LeftTopBar(R.string.exchange_title)
        Column(modifier = Modifier.horizontalScreenMargin()) {
            ExchangeListFilter(uiState = uiState, onExecuteCommand = onExecuteCommand)
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
                onRefresh = { onExecuteCommand(ExchangeListCommand.GetAllExchanges) }
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(top = Spacing.twelve),
                    verticalArrangement = Arrangement.spacedBy(Spacing.sixteen)
                ) {
                    if (uiState.isLoading) {
                        items(7) {
                            ExchangeItemShimmer(shimmerOffset = shimmerOffset)
                        }
                    } else {
                        items(uiState.exchangeScreenList) {
                            ExchangeItem(
                                onExecuteCommand = onExecuteCommand,
                                exchangeScreenModel = it
                            )
                        }
                        item {
                            Spacer(Modifier.windowInsetsPadding(WindowInsets.navigationBars))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ExchangeItem(
    onExecuteCommand: (Command<ExchangeListCommandReceiver>) -> Unit,
    exchangeScreenModel: ExchangeScreenModel,
) = with(exchangeScreenModel) {
    ElevatedCard(
        modifier = Modifier.fillMaxSize(),
        onClick = { onExecuteCommand(ExchangeListCommand.NavigateToDetail(exchangeScreenModel.id)) },
    ) {
        Column(
            modifier = Modifier.padding(screenMargin),
            verticalArrangement = Arrangement.spacedBy(Spacing.eight)
        ) {
            Text(
                text = name ?: stringResource(R.string.exchange_without_name),
                style = MaterialTheme.typography.titleMedium
            )
            HorizontalDivider()
            ExchangeDataRow(dataRows = dataRows)
        }
    }
}

@Composable
private fun ExchangeDataRow(dataRows: List<ExchangeListRowScreenModel>) = dataRows.forEach {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Spacing.four)
    ) {
        Text(
            modifier = Modifier.widthIn(max = 160.dp),
            text = stringResource(it.labelId).plus(":"),
            style = MaterialTheme.typography.bodyMedium,
        )
        Text(
            text = it.value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun ExchangeListFilter(
    uiState: ExchangeListUiState,
    onExecuteCommand: (Command<ExchangeListCommandReceiver>) -> Unit
) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        textValue = uiState.exchangeFilter,
        labelText = stringResource(R.string.exchange_filter),
        trailingIcon = TextFieldIcon.CLEAR_TEXT,
        leadingIcon = TextFieldIcon.SEARCH
    ) {
        onExecuteCommand(ExchangeListCommand.ExchangeFilter(it))
    }
}

@Composable
private fun ExchangeItemShimmer(shimmerOffset: Float) {
    Shimmer(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp),
        offset = shimmerOffset,
    )
}

@Composable
private fun DialogHandler(
    uiState: ExchangeListUiState,
    onExecuteCommand: (Command<ExchangeListCommandReceiver>) -> Unit
) = uiState.simpleDialogParam?.let {
    SimpleDialog(it) {
        onExecuteCommand(ExchangeListCommand.DismissSimpleDialog)
    }
}

@Composable
private fun LaunchedEffectHandler(
    uiEvent: Flow<ExchangeListUiEvent>,
    navController: NavHostController,
) {
    LaunchedEffect(Unit) {
        uiEvent.collect {
            when (it) {
                is ExchangeListUiEvent.NavigateTo -> navController.navigateTo(it.navigationModel)
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    ComposeTestTheme {
        ExchangeListScreen(
            uiState = ExchangeListUiState(
                exchangeScreenList = listOf(
                    ExchangeScreenModel(
                        id = "binance_id",
                        name = "Binance",
                        dataRows = listOf(
                            ExchangeListRowScreenModel(
                                labelId = R.string.exchange_id_label,
                                value = "binanc_id"
                            ),
                            ExchangeListRowScreenModel(
                                labelId = R.string.exchange_volume_1day_label,
                                value = "$1.000.000,00"
                            ),
                        ),
                    ),
                    ExchangeScreenModel(
                        id = "binance_id",
                        name = "Binance",
                        dataRows = listOf(
                            ExchangeListRowScreenModel(
                                labelId = R.string.exchange_id_label,
                                value = "binanc_id"
                            ),
                            ExchangeListRowScreenModel(
                                labelId = R.string.exchange_volume_1day_label,
                                value = "$1.000.000,00"
                            ),
                        ),
                    )
                )
            ),
        )
    }
}