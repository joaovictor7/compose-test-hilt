package com.composetest.feature.exchange.ui.detail

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.composetest.core.designsystem.components.linktext.LinkText
import com.composetest.core.designsystem.components.scaffolds.ScreenScaffold
import com.composetest.core.designsystem.components.topbar.LeftTopBar
import com.composetest.core.designsystem.dimensions.Spacing
import com.composetest.core.designsystem.extensions.horizontalScreenMargin
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.ui.interfaces.Command
import com.composetest.core.ui.interfaces.Screen
import com.composetest.feature.exchange.R
import com.composetest.feature.exchange.models.ExchangeDetailRowScreenModel
import kotlinx.coroutines.flow.Flow

internal object ExchangeDetailScreen :
    Screen<ExchangeDetailUiState, ExchangeDetailUiEvent, ExchangeDetailCommandReceiver> {

    @Composable
    override operator fun invoke(
        uiState: ExchangeDetailUiState,
        uiEvent: Flow<ExchangeDetailUiEvent>?,
        onExecuteCommand: (Command<ExchangeDetailCommandReceiver>) -> Unit
    ) {
        ScreenScaffold(
            modifier = Modifier.horizontalScreenMargin(),
            topBar = { LeftTopBar(titleId = R.string.exchange_detail_title) }
        ) {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(Spacing.twelve)) {
                items(uiState.exchangeDataRowsScreen) {
                    if (it.valueIsGrid) {
                        GridDataRow(exchangeDataRowScreen = it)
                    } else {
                        LabelDataRow(exchangeDataRowScreen = it)
                    }
                }
            }
        }
    }
}

@Composable
private fun LabelDataRow(
    exchangeDataRowScreen: ExchangeDetailRowScreenModel
) = with(exchangeDataRowScreen) {
    Row(horizontalArrangement = Arrangement.spacedBy(Spacing.four)) {
        LabelTextDataRow(labelId = labelId)
        if (isLink && value != null && url != null) {
            LinkText(
                linkText = value,
                url = url,
                textStyle = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
            )
        } else {
            Text(
                text = value ?: stringResource(R.string.exchange_detail_no_value),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun GridDataRow(
    exchangeDataRowScreen: ExchangeDetailRowScreenModel
) = with(exchangeDataRowScreen) {
    Column {
        LabelTextDataRow(labelId = labelId)
        Spacer(Modifier.height(Spacing.eight))
        gridValues.forEach {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max),
            ) {
                it.forEach {
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .setBackgroundGrid(it.second)
                            .border(width = 1.dp, color = MaterialTheme.colorScheme.outline),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            modifier = Modifier.padding(Spacing.four),
                            text = it.first ?: stringResource(R.string.exchange_detail_no_value),
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center,
                            fontWeight = if (it.second) FontWeight.Bold else null
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun LabelTextDataRow(@StringRes labelId: Int) {
    Text(
        text = stringResource(labelId).plus(":"),
        style = MaterialTheme.typography.bodyMedium,
    )
}

@Composable
private fun Modifier.setBackgroundGrid(isSet: Boolean) = also {
    if (isSet) return background(color = MaterialTheme.colorScheme.surfaceContainerHigh)
}

@Composable
@PreviewLightDark
private fun Preview() {
    ComposeTestTheme {
        ExchangeDetailScreen(
            uiState = ExchangeDetailUiState(
                exchangeDataRowsScreen = listOf(
                    ExchangeDetailRowScreenModel(
                        labelId = R.string.exchange_detail_title,
                        value = "coinapi.io",
                        url = "https://www.coinapi.io"
                    ),
                    ExchangeDetailRowScreenModel(
                        labelId = R.string.exchange_detail_title,
                        value = "datas",
                        gridValues = listOf(
                            listOf("1" to true, "2" to true, "3" to true),
                            listOf("4" to true, "5" to false, "6" to false),
                            listOf("7" to true, "8" to false, "9" to false),
                        ),
                    )
                )
            ),
            uiEvent = null
        ) { }
    }
}