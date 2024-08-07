package com.composetest.feature.home.ui.home3

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.composetest.core.ui.interfaces.Command
import com.composetest.core.ui.interfaces.Screen
import com.composetest.core.designsystem.theme.ComposeTestTheme

internal object Home3Screen : Screen<Home3UiState, Home3CommandReceiver> {

    @Composable
    override fun invoke(
        uiState: Home3UiState,
        onExecuteCommand: (Command<Home3CommandReceiver>) -> Unit
    ) {
        Column(
            modifier = Modifier
                .safeDrawingPadding()
                .fillMaxSize()
        ) {
            Button(onClick = { onExecuteCommand(Home3Commands.ReturnLogin) }) {
                Text(text = uiState.t)
            }
            Text(text = "Home3")
        }
        BackHandler {
            onExecuteCommand(Home3Commands.ReturnLogin)
        }
    }
}

@Preview
@Composable
private fun Preview() {
    ComposeTestTheme {
        Home3Screen(uiState = Home3UiState()) { }
    }
}