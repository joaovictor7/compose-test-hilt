package com.composetest.core.designsystem.extension

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit

fun TextStyle.toSpanStyle(
    color: Color = this.color,
    fontSize: TextUnit = this.fontSize,
    fontWeight: FontWeight? = this.fontWeight,
    fontStyle: FontStyle? = this.fontStyle,
    fontFamily: FontFamily? = this.fontFamily,
    letterSpacing: TextUnit = this.letterSpacing,
    background: Color = this.background,
    textDecoration: TextDecoration? = this.textDecoration
) = SpanStyle(
    color = color,
    fontSize = fontSize,
    fontWeight = fontWeight,
    fontStyle = fontStyle,
    fontFamily = fontFamily,
    letterSpacing = letterSpacing,
    background = background,
    textDecoration = textDecoration
)