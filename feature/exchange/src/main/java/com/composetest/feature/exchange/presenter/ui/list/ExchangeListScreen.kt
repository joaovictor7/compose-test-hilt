package com.composetest.feature.exchange.presenter.ui.list

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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.composetest.core.designsystem.component.dialog.SimpleDialog
import com.composetest.core.designsystem.component.pulltorefresh.PullToRefresh
import com.composetest.core.designsystem.component.shimmer.Shimmer
import com.composetest.core.designsystem.component.textfield.TextField
import com.composetest.core.designsystem.component.topbar.LeftTopBar
import com.composetest.core.designsystem.dimension.Spacing
import com.composetest.core.designsystem.dimension.screenMargin
import com.composetest.core.designsystem.enums.textfield.TextFieldIcon
import com.composetest.core.designsystem.extension.horizontalScreenMargin
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.designsystem.util.getSharedShimmerOffset
import com.composetest.core.router.extension.navigateTo
import com.composetest.core.ui.interfaces.Intent
import com.composetest.core.ui.util.UiEventsObserver
import com.composetest.feature.exchange.R
import com.composetest.feature.exchange.presenter.model.ExchangeListRowScreenModel
import com.composetest.feature.exchange.presenter.model.ExchangeScreenModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
internal fun ExchangeListScreen(
    uiState: ExchangeListUiState,
    uiEvent: Flow<ExchangeListUiEvent> = emptyFlow(),
    onExecuteIntent: (Intent<ExchangeListIntentReceiver>) -> Unit = {},
    navController: NavHostController = rememberNavController(),
) {
    val shimmerOffset by getSharedShimmerOffset()
    UiEventsHandler(uiEvent = uiEvent, navController = navController)
    DialogHandler(uiState = uiState, onExecuteIntent = onExecuteIntent)
    Column(modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)) {
        LeftTopBar(R.string.exchange_title)
        Column(modifier = Modifier.horizontalScreenMargin()) {
            ExchangeListFilter(uiState = uiState, onExecuteIntent = onExecuteIntent)
            PullToRefresh(
                isRefreshing = uiState.isLoading,
                onRefresh = { onExecuteIntent(ExchangeListIntent.GetAllExchanges) }
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(top = Spacing.medium),
                    verticalArrangement = Arrangement.spacedBy(Spacing.semiLarge)
                ) {
                    if (uiState.isLoading) {
                        items(7) {
                            ExchangeItemShimmer(shimmerOffset = shimmerOffset)
                        }
                    } else {
                        items(uiState.exchangeScreenList) {
                            ExchangeItem(
                                onExecuteIntent = onExecuteIntent,
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
    onExecuteIntent: (Intent<ExchangeListIntentReceiver>) -> Unit,
    exchangeScreenModel: ExchangeScreenModel,
) = with(exchangeScreenModel) {
    ElevatedCard(
        modifier = Modifier.fillMaxSize(),
        onClick = { onExecuteIntent(ExchangeListIntent.NavigateToDetail(exchangeScreenModel.id)) },
    ) {
        Column(
            modifier = Modifier.padding(screenMargin),
            verticalArrangement = Arrangement.spacedBy(Spacing.small)
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
        horizontalArrangement = Arrangement.spacedBy(Spacing.tiny)
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
    onExecuteIntent: (Intent<ExchangeListIntentReceiver>) -> Unit
) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        textValue = uiState.exchangeFilter,
        labelText = stringResource(R.string.exchange_filter),
        trailingIcon = TextFieldIcon.CLEAR_TEXT,
        leadingIcon = TextFieldIcon.SEARCH
    ) {
        onExecuteIntent(ExchangeListIntent.ExchangeFilter(it))
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
    onExecuteIntent: (Intent<ExchangeListIntentReceiver>) -> Unit
) = uiState.simpleDialogParam?.let {
    SimpleDialog(it) {
        onExecuteIntent(ExchangeListIntent.DismissSimpleDialog)
    }
}

@Composable
private fun UiEventsHandler(
    uiEvent: Flow<ExchangeListUiEvent>,
    navController: NavHostController,
) {
    UiEventsObserver(uiEvent) {
        when (it) {
            is ExchangeListUiEvent.NavigateTo -> navController.navigateTo(it.navigationModel)
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