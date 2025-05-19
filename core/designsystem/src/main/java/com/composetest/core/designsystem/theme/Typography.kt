package com.composetest.core.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.composetest.core.designsystem.dimension.FontSize

internal val typography = Typography(
    titleLarge = TextStyle(
        fontSize = FontSize.twentyTwo,
        lineHeight = 28.sp
    ),
    titleMedium = TextStyle(
        fontSize = FontSize.sixteen,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    titleSmall = TextStyle(
        fontSize = FontSize.fourteen,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    bodyLarge = TextStyle(
        fontSize = FontSize.sixteen,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontSize = FontSize.fourteen,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    bodySmall = TextStyle(
        fontSize = FontSize.twelve,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),
    headlineLarge = TextStyle(
        fontSize = FontSize.thirtyTwo,
        lineHeight = 40.sp
    ),
    headlineMedium = TextStyle(
        fontSize = FontSize.twentyEight,
        lineHeight = 36.sp
    ),
    headlineSmall = TextStyle(
        fontSize = FontSize.twentyFour,
        lineHeight = 32.sp
    ),
    labelLarge = TextStyle(
        fontSize = FontSize.fourteen,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    labelMedium = TextStyle(
        fontSize = FontSize.twelve,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontSize = FontSize.eleven,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    displayLarge = TextStyle(
        fontSize = FontSize.fiftySeven,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp
    ),
    displayMedium = TextStyle(
        fontSize = FontSize.fortyFive,
        lineHeight = 52.sp
    ),
    displaySmall = TextStyle(
        fontSize = FontSize.thirtySix,
        lineHeight = 44.sp
    )
)