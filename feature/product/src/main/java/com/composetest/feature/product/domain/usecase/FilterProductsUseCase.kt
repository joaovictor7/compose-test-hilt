package com.composetest.feature.product.domain.usecase

import com.composetest.feature.product.domain.model.ProductModel
import java.text.Normalizer
import javax.inject.Inject

internal class FilterProductsUseCase @Inject constructor() {

    operator fun invoke(productList: List<ProductModel>, filter: String): List<ProductModel> {
        if (filter.isBlank()) return productList
        val normalizedTerms = filter.normalize().split(' ').filter { it.isNotEmpty() }
        return productList.filter { product ->
            val textToSearch = "${product.title} ${product.description}".normalize()
            normalizedTerms.all { textToSearch.contains(it) }
        }
    }

    private fun String.normalize(): String {
        val normalizedText = Normalizer.normalize(this, Normalizer.Form.NFD)
        return normalizeTextRegex.replace(normalizedText, String()).lowercase()
    }

    private companion object {
        val normalizeTextRegex = "\\p{InCombiningDiacriticalMarks}+".toRegex()
    }
}