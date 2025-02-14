package com.composetest.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSplashScreen()
        uiStateObserver()
        setContent {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            MainScreen(
                uiState = uiState,
                onExecuteCommand = viewModel::executeCommand
            )
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.executeCommand(MainCommand.FetchRemoteConfig)
    }

    private fun uiStateObserver() = lifecycleScope.launch {
        viewModel.uiState.flowWithLifecycle(lifecycle).collect { uiState ->
            setEdgeToEdge(uiState)
        }
    }

    private fun setEdgeToEdge(uiState: MainUiState) {
        enableEdgeToEdge(uiState.statusBarStyle, uiState.navigationBarStyle)
        if (uiState.forceNavigationBarTransparency) {
            window.isNavigationBarContrastEnforced = false
        }
    }

    private fun setSplashScreen() {
        installSplashScreen().setKeepOnScreenCondition {
            viewModel.uiState.value.showSplashScreen
        }
    }
}