package com.composetest.core.designsystem.component.rating

import com.composetest.core.designsystem.component.rating.enums.RatingStatus
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.composetest.core.designsystem.dimension.Spacing
import com.composetest.core.designsystem.extension.toDp

@Composable
fun Rating(
    modifier: Modifier,
    ratingStatus: RatingStatus,
    rating: String,
) {
    var dividerHeight by remember { mutableStateOf(IntSize.Zero) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Spacing.small)
    ) {
        Text(
            text = rating,
            style = MaterialTheme.typography.titleMedium
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier.onSizeChanged {
                dividerHeight = it
            },
        ) {
            if (ratingStatus == RatingStatus.NEUTRAL) {
                HorizontalDivider(
                    modifier = Modifier.width(dividerHeight.width.toDp),
                    thickness = 2.dp,
                    color = ratingStatus.iconColor,
                )
            } else if (ratingStatus.iconId != null) {
                Icon(
                    painter = painterResource(ratingStatus.iconId),
                    contentDescription = null,
                    tint = ratingStatus.iconColor,
                    modifier = Modifier.size(
                        width = dividerHeight.width.toDp,
                        height = dividerHeight.height.toDp
                    )
                )
            }
        }
    }
}