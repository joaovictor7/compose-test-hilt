package com.composetest.core.domain.usecases.news

import com.composetest.core.domain.repositories.NewsApiRepository
import javax.inject.Inject

class GetTopHeadlinesUseCase @Inject constructor(
    private val repository: NewsApiRepository
) {
    suspend operator fun invoke() = repository.getTopHeadlinesNews()
        .filterNot { it.title.contains(REMOVED_NEWS, true) }
        .sortedBy { it.publishedAt }

    private companion object {
        const val REMOVED_NEWS = "Removed"
    }
}