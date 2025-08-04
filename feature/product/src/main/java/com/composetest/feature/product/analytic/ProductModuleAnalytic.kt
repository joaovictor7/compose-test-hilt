package com.composetest.feature.product.analytic

import com.composetest.core.analytic.api.screen.ScreenAnalytic

internal abstract class ProductModuleAnalytic : ScreenAnalytic {
    final override val feature = "product"
}