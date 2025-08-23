package com.composetest.core.designsystem.component.rating.enums

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.composetest.core.designsystem.R

private val like = Color(0xFF2ECC71)
private val dislike = Color(0xFFE74C3C)
private val neutral = Color(0xFF9E9E9E)

enum class RatingStatus(
    @param:DrawableRes val iconId: Int? = null,
    val iconColor: Color,
) {
    LIKE(R.drawable.ic_like, like),
    DISLIKE(R.drawable.ic_dislike, dislike),
    NEUTRAL(iconColor = neutral);

    companion object {
        fun getStatus(rating: Double) = if (rating < 3) {
            DISLIKE
        } else if (rating > 4) {
            LIKE
        } else {
            NEUTRAL
        }
    }
}