package com.composetest.feature.home.ui.home2

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

internal object Home2Screen : Screen<Home2UiState, Home2CommandReceiver> {

    @Composable
    override fun invoke(
        uiState: Home2UiState,
        onExecuteCommand: (Command<Home2CommandReceiver>) -> Unit
    ) {
        Column(
            modifier = Modifier
                .safeDrawingPadding()
                .fillMaxSize()
        ) {
            Button(onClick = { onExecuteCommand(Home2Commands.ReturnHome) }) {
                Text(text = uiState.t)
            }
            Text(text = "Home2")
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