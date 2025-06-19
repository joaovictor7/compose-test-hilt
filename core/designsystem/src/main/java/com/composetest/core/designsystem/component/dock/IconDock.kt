package com.composetest.core.designsystem.component.dock

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.composetest.core.designsystem.R
import com.composetest.core.designsystem.dimension.Spacing
import com.composetest.core.designsystem.extension.opacity
import com.composetest.core.designsystem.param.dock.IconDockParam
import com.composetest.core.designsystem.theme.ComposeTestTheme

/**
 * @param modifier set [height] is mandatory for correctly component show
 */
@Composable
fun IconDock(
    modifier: Modifier,
    shape: Shape,
    selectedIndex: Int,
    dockItems: List<IconDockParam>,
    onSelectionChange: (selectedIndex: Int) -> Unit
) {
    if (dockItems.isEmpty()) return
    val internalPadding = Spacing.micro
    val zeroSpacing = Spacing.none
    val currentDensity = LocalDensity.current
    val containerColor = TabRowDefaults.primaryContainerColor.opacity(0.7f)
    var componentHeight by remember { mutableStateOf(zeroSpacing) }
    Row(
        modifier = modifier
            .background(color = containerColor, shape = shape)
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outlineVariant
                ),
                shape = shape
            )
    ) {
        TabRow(
            modifier = Modifier
                .fillMaxSize()
                .padding(internalPadding)
                .clip(shape)
                .onGloballyPositioned {
                    componentHeight = it.size.height.convertToDp(currentDensity)
                },
            containerColor = containerColor,
            selectedTabIndex = selectedIndex,
            divider = {},
            indicator = { tabPositions ->
                if (selectedIndex < tabPositions.size) {
                    Spacer(
                        modifier = Modifier
                            .tabIndicatorOffset(tabPositions[selectedIndex])
                            .padding(internalPadding)
                            .background(
                                color = MaterialTheme.colorScheme.onSurface.opacity(0.12f),
                                shape = shape
                            )
                            .fillMaxHeight()
                    )
                }
            }
        ) {
            DockItems(
                height = componentHeight,
                internalPadding = internalPadding,
                shape = shape,
                dockItems = dockItems,
                selectedIndex = selectedIndex,
                onSelectionChange = onSelectionChange
            )
        }
    }
}

@Composable
private fun DockItems(
    height: Dp,
    internalPadding: Dp,
    shape: Shape,
    dockItems: List<IconDockParam>,
    selectedIndex: Int,
    onSelectionChange: (selectedIndex: Int) -> Unit
) = dockItems.forEach { dockItem ->
    Tab(
        modifier = Modifier
            .height(height)
            .padding(internalPadding)
            .clip(shape),
        selected = selectedIndex == dockItem.index,
        onClick = { onSelectionChange(dockItem.index) },
        unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
    ) {
        Icon(
            painter = painterResource(dockItem.iconId),
            contentDescription = dockItem.contentDescription
        )
    }
}

private fun Int.convertToDp(density: Density) = with(density) { this@convertToDp.toDp() }

@Composable
@PreviewLightDark
private fun Preview() {
    ComposeTestTheme {
        var selectedIndex by remember { mutableIntStateOf(2) }
        Box(modifier = Modifier.background(color = Color.Red)) {
            IconDock(
                modifier = Modifier.height(50.dp),
                shape = MaterialTheme.shapes.extraLarge,
                selectedIndex = selectedIndex,
                dockItems = listOf(
                    IconDockParam(0, R.drawable.ic_house_filled),
                    IconDockParam(1, R.drawable.ic_cancel),
                    IconDockParam(2, R.drawable.ic_search),
                    IconDockParam(3, R.drawable.ic_visibility_off),
                )
            ) {
                selectedIndex = it
            }
        }
    }
}