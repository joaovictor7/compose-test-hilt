package com.composetest.core.data.mappers

import com.composetest.core.data.api.responses.newsapi.NewsApiSettingsResponse
import com.composetest.core.data.utils.decodeJson
import com.composetest.core.domain.models.NewsApiSettings
import javax.inject.Inject

internal class NewsApiSettingsMapper @Inject constructor() {

    operator fun invoke(json: String) = decodeJson<NewsApiSettingsResponse>(json).let {
        NewsApiSettings(apiKey = it.apiKey, url = it.url)
    }
}