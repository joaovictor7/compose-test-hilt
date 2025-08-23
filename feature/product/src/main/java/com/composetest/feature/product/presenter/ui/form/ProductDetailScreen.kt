package com.composetest.feature.product.presenter.ui.form

import com.composetest.core.designsystem.component.rating.enums.RatingStatus
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.composetest.core.designsystem.component.asyncimage.AsyncImage
import com.composetest.core.designsystem.component.rating.Rating
import com.composetest.core.designsystem.component.scaffold.ScreenScaffold
import com.composetest.core.designsystem.component.topbar.LeftTopBar
import com.composetest.core.designsystem.dimension.Spacing
import com.composetest.core.designsystem.extension.horizontalScreenMargin
import com.composetest.core.designsystem.extension.stringResource
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.feature.product.R
import com.composetest.feature.product.presenter.model.ProductDetailRow
import com.composetest.feature.product.presenter.ui.form.viewmodel.ProductDetailUiState

private val minImageHeight = 100.dp
private val maxImageHeight = 250.dp

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun ProductDetailScreen(uiState: ProductDetailUiState) {
    val scrollState = rememberScrollState()
    val imageHeight by remember {
        derivedStateOf {
            val newHeight = maxImageHeight - (scrollState.value / 2).dp
            newHeight.coerceAtLeast(minImageHeight)
        }
    }
    ScreenScaffold(
        modifier = Modifier.horizontalScreenMargin(),
        topBar = { LeftTopBar(titleId = R.string.product_title) },
    ) {
        uiState.thumbnail?.let {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                AsyncImage(
                    url = it,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(imageHeight),
                )
            }
        }
        Column(
            modifier = Modifier.verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(Spacing.medium)
        ) {
            Text(
                text = uiState.title,
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = uiState.description,
                style = MaterialTheme.typography.bodyLarge,
            )
            Spacer(Modifier.height(Spacing.medium))
            ProductDetailRow(uiState.infoRows)
        }
    }
}

@Composable
private fun ProductDetailRow(infoRows: List<ProductDetailRow>) = infoRows.forEach { row ->
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(row.labelId),
            style = MaterialTheme.typography.bodyLarge,
        )
        if (row.isRating) {
            Rating(
                modifier = Modifier.size(20.dp),
                rating = row.text,
                ratingStatus = RatingStatus.getStatus(row.text.toDouble()),
            )
        } else {
            Text(
                text = row.text,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
    HorizontalDivider()
}

@PreviewLightDark
@Composable
private fun PreviewProductDetailScreen() {
    ComposeTestTheme {
        ProductDetailScreen(
            ProductDetailUiState(
                title = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                description = "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
                thumbnail = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
                infoRows = listOf(
                    ProductDetailRow(R.string.product_detail_price, "109.95"),
                    ProductDetailRow(R.string.product_detail_price, "men's clothing"),
                    ProductDetailRow(R.string.product_detail_price, "3.9", true),
                    ProductDetailRow(R.string.product_detail_price, "120"),
                )
            )
        )
    }
}


