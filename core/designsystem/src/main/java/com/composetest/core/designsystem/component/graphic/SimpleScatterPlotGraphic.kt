package com.composetest.core.designsystem.component.graphic

import android.graphics.Paint
import androidx.annotation.FloatRange
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.composetest.core.designsystem.dimension.FontSize
import com.composetest.core.designsystem.dimension.Spacing
import com.composetest.core.designsystem.theme.ComposeTestTheme

@Composable
fun SimpleScatterPlotGraphic(
    modifier: Modifier = Modifier,
    yPoints: List<Float>,
    @FloatRange(from = 1.0) labelCount: Float = 5f,
    minLabel: Float,
    maxLabel: Float,
    labelFormat: String? = null
) {
    val graphColor = MaterialTheme.colorScheme.primary
    val lineColor = MaterialTheme.colorScheme.primary
    val drawCircleColor = MaterialTheme.colorScheme.secondary
    val labelTextColor = MaterialTheme.colorScheme.onSurface
    val spacing = 40.dp
    val isSingleValue = maxLabel == minLabel

    Canvas(
        modifier = modifier
            .padding(Spacing.semiLarge)
            .fillMaxWidth()
    ) {
        val spacingPx = spacing.toPx()
        val spacePerHour = if (yPoints.size > 1) {
            (size.width - spacingPx) / (yPoints.size - 1)
        } else {
            0f
        }
        val normX = mutableListOf<Float>()
        val normY = mutableListOf<Float>()
        val strokePath = Path().apply {
            for (i in yPoints.indices) {
                val currentX = spacingPx + i * spacePerHour
                val normalizedY = if (isSingleValue) {
                    size.height / 2f
                } else {
                    size.height - (yPoints[i] - minLabel) / (maxLabel - minLabel) * size.height
                }

                if (i == 0) {
                    moveTo(currentX, normalizedY)
                } else {
                    val previousX = spacingPx + (i - 1) * spacePerHour
                    val previousY =
                        size.height - (yPoints[i - 1] - minLabel) / (maxLabel - minLabel) * size.height

                    val conX1 = (previousX + currentX) / 2f
                    val conX2 = (previousX + currentX) / 2f

                    cubicTo(
                        x1 = conX1,
                        y1 = previousY,
                        x2 = conX2,
                        y2 = normalizedY,
                        x3 = currentX,
                        y3 = normalizedY
                    )
                }

                // Circle dot points
                normX.add(currentX)
                normY.add(normalizedY)
            }
        }

        drawPath(
            path = strokePath,
            color = graphColor,
            style = Stroke(
                width = 3.dp.toPx(),
                cap = StrokeCap.Round
            )
        )

        (normX.indices).forEach {
            drawCircle(
                color = drawCircleColor,
                radius = 4.dp.toPx(),
                center = Offset(normX[it], normY[it])
            )
        }

        // Draw Y axis labels
        val labelCountDec = labelCount.dec()
        val labelSpacing = if (isSingleValue) {
            0f
        } else {
            (maxLabel - minLabel) / labelCountDec
        }
        for (i in 0..labelCountDec.toInt()) {
            val yValue = minLabel + i * labelSpacing
            val yValueFormatted = if (yValue % 1.0 == 0.0) {
                yValue.toInt().toString()
            } else {
                "%.1f".format(yValue).replace(",", ".")
            }
            val yPosition = if (isSingleValue) {
                size.height / 2f
            } else {
                size.height - (yValue - minLabel) / (maxLabel - minLabel) * size.height
            }

            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    labelFormat?.format(yValueFormatted) ?: yValueFormatted,
                    0f,
                    yPosition,
                    Paint().apply {
                        color = labelTextColor.toArgb()
                        textSize = FontSize.fourteen.toPx()
                        style = Paint.Style.FILL_AND_STROKE
                    }
                )

                // Draw horizontal line for each label
                drawLine(
                    start = Offset(x = spacing.toPx(), y = yPosition),
                    end = Offset(x = size.width, y = yPosition),
                    color = lineColor,
                    strokeWidth = 0.3.dp.toPx()
                )
            }
        }
    }
}

@Composable
@PreviewLightDark
private fun Preview() {
    ComposeTestTheme {
        SimpleScatterPlotGraphic(
            modifier = Modifier.height(200.dp),
            yPoints = listOf(22f),
            labelCount = 5f,
            minLabel = 20f,
            maxLabel = 20f,
            labelFormat = "%sº"
        )
    }
}