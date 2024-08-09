package com.composetest.core.designsystem.theme

import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import com.composetest.core.designsystem.compositions.LocalThemeProvider
import com.composetest.core.designsystem.extensions.isDarkMode
import com.composetest.core.domain.enums.Theme

@Composable
fun ComposeTestTheme(
    theme: Theme = Theme.AUTO,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = getColorScheme(dynamicColor, theme)
    CompositionLocalProvider(LocalThemeProvider provides theme) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography,
            content = {
                Surface(content = content)
            }
        )
    }
}

@Composable
private fun getColorScheme(dynamicColor: Boolean, theme: Theme) = when {
    dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        val context = LocalContext.current
        if (theme.isDarkMode) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    }
    theme.isDarkMode -> darkColorScheme
    else -> lightColorScheme
}