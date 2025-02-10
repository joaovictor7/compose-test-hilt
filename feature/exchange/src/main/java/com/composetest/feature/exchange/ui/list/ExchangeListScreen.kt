package com.composetest.feature.exchange.ui.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.composetest.core.designsystem.components.dialogs.SimpleDialog
import com.composetest.core.designsystem.components.textfields.TextField
import com.composetest.core.designsystem.components.topbar.LeftTopBar
import com.composetest.core.designsystem.constants.screenMargin
import com.composetest.core.designsystem.dimensions.Spacing
import com.composetest.core.designsystem.enums.textfields.TextFieldIcon
import com.composetest.core.designsystem.extensions.screenMargin
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.ui.interfaces.Command
import com.composetest.core.ui.interfaces.Screen
import com.composetest.feature.exchange.R
import com.composetest.feature.exchange.models.ExchangeListRowScreenModel
import com.composetest.feature.exchange.models.ExchangeScreenModel
import kotlinx.coroutines.flow.Flow

internal object ExchangeListScreen :
    Screen<ExchangeListUiState, ExchangeListUiEvent, ExchangeListCommandReceiver> {

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    override operator fun invoke(
        uiState: ExchangeListUiState,
        uiEvent: Flow<ExchangeListUiEvent>?,
        onExecuteCommand: (Command<ExchangeListCommandReceiver>) -> Unit
    ) {
        val pullToRefreshState = rememberPullToRefreshState()
        DialogsHandler(uiState = uiState, onExecuteCommand = onExecuteCommand)
        Column(
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.statusBars)
                .screenMargin()
        ) {
            LeftTopBar(R.string.exchange_title)
            Column {
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
                        items(uiState.exchangeScreenList) {
                            ExchangeItem(onExecuteCommand = onExecuteCommand, exchangeScreenModel = it)
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
private fun DialogsHandler(
    uiState: ExchangeListUiState,
    onExecuteCommand: (Command<ExchangeListCommandReceiver>) -> Unit
) = uiState.simpleDialogParam?.let {
    SimpleDialog(it) {
        onExecuteCommand(ExchangeListCommand.DismissSimpleDialog)
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
            uiEvent = null
        ) {}
    }
}