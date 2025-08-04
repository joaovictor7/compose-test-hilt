package com.composetest.feature.product.presenter.ui.list

import RatingStatus
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.composetest.core.designsystem.component.pulltorefresh.PullToRefresh
import com.composetest.core.designsystem.component.rating.Rating
import com.composetest.core.designsystem.component.shimmer.Shimmer
import com.composetest.core.designsystem.component.textfield.TextField
import com.composetest.core.designsystem.component.textfield.enums.TextFieldIcon
import com.composetest.core.designsystem.dimension.Spacing
import com.composetest.core.designsystem.dimension.screenMargin
import com.composetest.core.designsystem.extension.horizontalScreenMargin
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.designsystem.util.getSharedShimmerOffset
import com.composetest.core.router.extension.navigateTo
import com.composetest.core.ui.interfaces.Intent
import com.composetest.core.ui.util.UiEventsObserver
import com.composetest.feature.product.R
import com.composetest.feature.product.presenter.model.ProductItemListModel
import com.composetest.feature.product.presenter.ui.list.viewmodel.ProductListIntent
import com.composetest.feature.product.presenter.ui.list.viewmodel.ProductListIntentReceiver
import com.composetest.feature.product.presenter.ui.list.viewmodel.ProductListUiEvent
import com.composetest.feature.product.presenter.ui.list.viewmodel.ProductListUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
internal fun ProductListScreen(
    uiState: ProductListUiState,
    uiEvent: Flow<ProductListUiEvent> = emptyFlow(),
    onExecuteIntent: (Intent<ProductListIntentReceiver>) -> Unit = {},
    navController: NavHostController = rememberNavController(),
) {
    val shimmerOffset by getSharedShimmerOffset()
    UiEventsHandler(uiEvent = uiEvent, navController = navController)
//    DialogHandler(uiState = uiState, onExecuteIntent = onExecuteIntent)
    Column(modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)) {
        Column(modifier = Modifier.horizontalScreenMargin()) {
            ProductListFilter(uiState = uiState, onExecuteIntent = onExecuteIntent)
            PullToRefresh(
                isRefreshing = uiState.isLoading,
                onRefresh = { onExecuteIntent(ProductListIntent.ResyncProducts) }
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
                        items(uiState.productItemList) {
                            ProductItem(
                                onExecuteIntent = onExecuteIntent,
                                productItemListModel = it
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
private fun ProductItem(
    onExecuteIntent: (Intent<ProductListIntentReceiver>) -> Unit,
    productItemListModel: ProductItemListModel,
) = with(productItemListModel) {
    ElevatedCard(
        modifier = Modifier.fillMaxSize(),
        onClick = { onExecuteIntent(ProductListIntent.NavigateToDetail(id)) },
    ) {
        Column(
            modifier = Modifier.padding(screenMargin),
            verticalArrangement = Arrangement.spacedBy(Spacing.small)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
            HorizontalDivider()
            ProductRate(productItemList = productItemListModel)
        }
    }
}

@Composable
private fun ProductRate(
    productItemList: ProductItemListModel,
) = with(productItemList) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Rate:",
            style = MaterialTheme.typography.titleMedium
        )
        Rating(
            modifier = Modifier.size(20.dp),
            rating = rating,
            ratingStatus = ratingStatus
        )
    }
}

@Composable
private fun ProductListFilter(
    uiState: ProductListUiState,
    onExecuteIntent: (Intent<ProductListIntentReceiver>) -> Unit
) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        textValue = uiState.filter,
        labelText = stringResource(R.string.product_filter),
        trailingIcon = TextFieldIcon.CLEAR_TEXT,
        leadingIcon = TextFieldIcon.SEARCH
    ) {
        onExecuteIntent(ProductListIntent.ProductFilter(it))
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

//@Composable
//private fun DialogHandler(
//    uiState: ProductListUiState,
//    onExecuteIntent: (Intent<ProductListIntentReceiver>) -> Unit
//) = uiState.simpleDialogParam?.let {
//    SimpleDialog(it) {
//        onExecuteIntent(ProductListIntent.DismissSimpleDialog)
//    }
//}

@Composable
private fun UiEventsHandler(
    uiEvent: Flow<ProductListUiEvent>,
    navController: NavHostController,
) {
    UiEventsObserver(uiEvent) {
        when (it) {
            is ProductListUiEvent.NavigateTo -> navController.navigateTo(it.navigationModel)
        }
    }
}

@Preview
@Composable
private fun Preview() {
    ComposeTestTheme {
        ProductListScreen(
            uiState = ProductListUiState(
                productItemList = listOf(
                    ProductItemListModel(
                        id = 1,
                        title = "Product 1",
                        rating = "4.5",
                        ratingStatus = RatingStatus.LIKE,
                    ),
                    ProductItemListModel(
                        id = 2,
                        title = "Product 2",
                        rating = "3.2",
                        ratingStatus = RatingStatus.DISLIKE,
                    ),
                    ProductItemListModel(
                        id = 3,
                        title = "Product 3",
                        rating = "5.0",
                        ratingStatus = RatingStatus.NEUTRAL,
                    )
                ),
                isLoading = false,
                filter = "filter"
            )
        )
    }
}