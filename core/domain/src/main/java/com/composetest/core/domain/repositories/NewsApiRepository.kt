package com.composetest.core.domain.repositories

import com.composetest.core.domain.models.news.ArticleModel

interface NewsApiRepository {
    suspend fun getTopHeadlinesNews(): List<ArticleModel>
}