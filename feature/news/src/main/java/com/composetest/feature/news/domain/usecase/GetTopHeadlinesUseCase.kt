package com.composetest.feature.news.domain.usecase

import com.composetest.feature.news.data.repositorie.NewsApiRepository
import javax.inject.Inject

internal class GetTopHeadlinesUseCase @Inject constructor(
    private val repository: NewsApiRepository
) {
    suspend operator fun invoke() = repository.getTopHeadlinesNews()
        .filterNot { it.title.contains(REMOVED_NEWS, true) }
        .sortedBy { it.publishedAt }

    private companion object {
        const val REMOVED_NEWS = "Removed"
    }
}