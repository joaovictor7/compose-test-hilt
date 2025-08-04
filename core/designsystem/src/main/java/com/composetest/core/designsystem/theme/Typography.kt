package com.composetest.core.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.composetest.core.designsystem.dimension.FontSize

internal val typography = Typography(
    titleLarge = TextStyle(
        fontSize = FontSize.extraLarge,
        lineHeight = 28.sp
    ),
    titleMedium = TextStyle(
        fontSize = FontSize.large,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    titleSmall = TextStyle(
        fontSize = FontSize.medium,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    bodyLarge = TextStyle(
        fontSize = FontSize.large,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontSize = FontSize.medium,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    bodySmall = TextStyle(
        fontSize = FontSize.small,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),
    headlineLarge = TextStyle(
        fontSize = FontSize.huge,
        lineHeight = 40.sp
    ),
    headlineMedium = TextStyle(
        fontSize = FontSize.xxxLarge,
        lineHeight = 36.sp
    ),
    headlineSmall = TextStyle(
        fontSize = FontSize.xxLarge,
        lineHeight = 32.sp
    ),
    labelLarge = TextStyle(
        fontSize = FontSize.medium,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    labelMedium = TextStyle(
        fontSize = FontSize.small,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontSize = FontSize.extraSmall,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    displayLarge = TextStyle(
        fontSize = FontSize.xxxHuge,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp
    ),
    displayMedium = TextStyle(
        fontSize = FontSize.xxHuge,
        lineHeight = 52.sp
    ),
    displaySmall = TextStyle(
        fontSize = FontSize.extraHuge,
        lineHeight = 44.sp
    )
)