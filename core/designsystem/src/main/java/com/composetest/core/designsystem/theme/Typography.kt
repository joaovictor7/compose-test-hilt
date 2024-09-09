package com.composetest.core.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.composetest.core.designsystem.dimensions.fontSizes

internal val typography
    @Composable get() = Typography(
        titleLarge = TextStyle(
            fontSize = fontSizes.twentyTwo,
            lineHeight = 28.sp
        ),
        titleMedium = TextStyle(
            fontSize = fontSizes.sixteen,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp
        ),
        titleSmall = TextStyle(
            fontSize = fontSizes.fourteen,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp
        ),
        bodyLarge = TextStyle(
            fontSize = fontSizes.sixteen,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp
        ),
        bodyMedium = TextStyle(
            fontSize = fontSizes.fourteen,
            lineHeight = 20.sp,
            letterSpacing = 0.25.sp
        ),
        bodySmall = TextStyle(
            fontSize = fontSizes.twelve,
            lineHeight = 16.sp,
            letterSpacing = 0.4.sp
        ),
        headlineLarge = TextStyle(
            fontSize = fontSizes.thirtyTwo,
            lineHeight = 40.sp
        ),
        headlineMedium = TextStyle(
            fontSize = fontSizes.twentyEight,
            lineHeight = 36.sp
        ),
        headlineSmall = TextStyle(
            fontSize = fontSizes.twentyFour,
            lineHeight = 32.sp
        ),
        labelLarge = TextStyle(
            fontSize = fontSizes.fourteen,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp
        ),
        labelMedium = TextStyle(
            fontSize = fontSizes.twelve,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        ),
        labelSmall = TextStyle(
            fontSize = fontSizes.eleven,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        ),
        displayLarge = TextStyle(
            fontSize = fontSizes.fiftySeven,
            lineHeight = 64.sp,
            letterSpacing = (-0.25).sp
        ),
        displayMedium = TextStyle(
            fontSize = fontSizes.fortyFive,
            lineHeight = 52.sp
        ),
        displaySmall = TextStyle(
            fontSize = fontSizes.thirtySix,
            lineHeight = 44.sp
        )
    )