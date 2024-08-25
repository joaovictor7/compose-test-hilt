package com.composetest.feature.home.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.composetest.core.designsystem.components.dock.DockVisibilityManager
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.ui.interfaces.Command
import com.composetest.core.ui.interfaces.Screen

internal object HomeScreen : Screen<HomeUiState, HomeCommandReceiver> {

    @Composable
    override fun invoke(
        uiState: HomeUiState,
        onExecuteCommand: (Command<HomeCommandReceiver>) -> Unit
    ) {
        val scrollState = rememberScrollState()
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            Button(onClick = { onExecuteCommand(HomeCommand.NavigateToHome2) }) {
                Text(text = "Go to Home 2")
            }
            Text(text = "Home1")
        }
        DockVisibilityManager(scrollState = scrollState) {
            onExecuteCommand(HomeCommand.RootDockVisibility(it))
        }
    }
}

@Preview
@Composable
private fun Preview() {
    ComposeTestTheme {
        HomeScreen(uiState = HomeUiState()) { }
    }
}