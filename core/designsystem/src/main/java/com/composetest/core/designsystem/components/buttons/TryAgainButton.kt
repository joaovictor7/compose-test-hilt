package com.composetest.core.designsystem.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.composetest.core.designsystem.R
import com.composetest.core.designsystem.dimensions.Spacing
import com.composetest.core.designsystem.theme.ComposeTestTheme

@Composable
fun TryAgainButton(
    modifier: Modifier = Modifier,
    onRetryClick: (() -> Unit)?
) {
    Column(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(top = Spacing.twelve),
            text = stringResource(R.string.global_word_try_again),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
        IconButton(onClick = { onRetryClick?.invoke() }) {
            Icon(
                painter = painterResource(R.drawable.ic_refresh_medium),
                contentDescription = null
            )
        }
    }
}

@Composable
@PreviewLightDark
private fun Preview() {
    ComposeTestTheme {
        TryAgainButton(modifier = Modifier.size(width = 300.dp, height = 500.dp)) { }
    }
}