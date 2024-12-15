package com.composetest.core.designsystem.components.labels

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.composetest.core.designsystem.R
import com.composetest.core.designsystem.dimensions.Spacing
import com.composetest.core.designsystem.theme.ComposeTestTheme

@Composable
fun DataLabel(
    @StringRes labelTitleId: Int,
    labelText: String
) {
    DataLabel(labelTitleId = labelTitleId, labelText = AnnotatedString(labelText))
}

@Composable
fun DataLabel(
    @StringRes labelTitleId: Int,
    labelText: AnnotatedString
) {
    Column {
        Column(
            modifier = Modifier.padding(horizontal = Spacing.eight, vertical = Spacing.twelve),
            verticalArrangement = Arrangement.spacedBy(Spacing.four)
        ) {
            Text(
                text = stringResource(labelTitleId),
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = labelText,
                style = MaterialTheme.typography.bodyLarge
            )
        }
        HorizontalDivider()
    }
}

@Composable
@PreviewLightDark
private fun Preview() {
    ComposeTestTheme {
        DataLabel(
            labelTitleId = R.string.global_word_close,
            labelText = "teste"
        )
    }
}