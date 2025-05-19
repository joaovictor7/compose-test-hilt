package com.composetest.core.designsystem.component.linktext

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.designsystem.theme.linkColor
import com.composetest.core.ui.extension.navigateToWebUrl

@Composable
fun LinkText(
    modifier: Modifier = Modifier,
    textStyle: TextStyle = LocalTextStyle.current,
    fontWeight: FontWeight? = null,
    linkText: String,
    url: String,
    onClick: (() -> Unit)? = null
) {
    LinkText(
        modifier = modifier,
        textStyle = textStyle,
        fontWeight = fontWeight,
        fullText = linkText,
        linkText = linkText,
        url = url,
        onClick = onClick
    )
}

@Composable
fun LinkText(
    modifier: Modifier = Modifier,
    textStyle: TextStyle = LocalTextStyle.current,
    fontWeight: FontWeight? = null,
    fullText: String,
    linkText: String,
    url: String,
    onClick: (() -> Unit)? = null
) {
    var textLayoutResult: TextLayoutResult? = null
    val context = LocalContext.current
    val annotatedString = buildAnnotatedString {
        val startIndex = fullText.indexOf(linkText)
        val endIndex = startIndex + linkText.length
        append(fullText)
        if (startIndex >= 0) {
            addStyle(
                style = SpanStyle(
                    color = linkColor,
                    textDecoration = TextDecoration.Underline
                ),
                start = startIndex,
                end = endIndex
            )
            addStringAnnotation(
                tag = "URL",
                annotation = url,
                start = startIndex,
                end = endIndex
            )
        }
    }
    BasicText(
        text = annotatedString,
        style = textStyle.copy(fontWeight = fontWeight),
        modifier = modifier
            .pointerInput(Unit) {
                detectTapGestures { position ->
                    textLayoutResult?.let { layoutResult ->
                        val offset = layoutResult.getOffsetForPosition(position)
                        annotatedString.getStringAnnotations(
                            tag = "URL",
                            start = offset,
                            end = offset
                        ).firstOrNull()?.let { annotation ->
                            context.navigateToWebUrl(annotation.item)
                            onClick?.invoke()
                        }
                    }
                }
            },
        onTextLayout = { layoutResult ->
            textLayoutResult = layoutResult
        },
    )
}

@Composable
@Preview
private fun Preview() {
    ComposeTestTheme {
        LinkText(linkText = "teste", url = "teste")
    }
}