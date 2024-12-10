package com.composetest.feature.home.ui.home2

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.composetest.core.designsystem.components.dock.DockExtraPadding
import com.composetest.core.designsystem.constants.topScreenMarginList
import com.composetest.core.designsystem.extensions.horizontalScreenMargin
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.ui.interfaces.Command
import com.composetest.core.ui.interfaces.Screen
import kotlinx.coroutines.flow.Flow

internal object Home2Screen : Screen<Home2UiState, Home2UiEvent, Home2CommandReceiver> {

    @Composable
    override fun invoke(
        uiState: Home2UiState,
        uiEvent: Flow<Home2UiEvent>?,
        onExecuteCommand: (Command<Home2CommandReceiver>) -> Unit
    ) {
        LazyColumn(
            modifier = Modifier.horizontalScreenMargin(),
            contentPadding = topScreenMarginList
        ) {
            item {
                Text(text = "Home22222222222222222222222222222222222222222222222222")
            }
            items(300) {
                Button(onClick = { onExecuteCommand(Home2Command.ReturnHome) }) {
                    Text(text = uiState.t)
                }
            }
            item {
                DockExtraPadding()
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    ComposeTestTheme {
        Home2Screen(uiState = Home2UiState()) { }
    }
}