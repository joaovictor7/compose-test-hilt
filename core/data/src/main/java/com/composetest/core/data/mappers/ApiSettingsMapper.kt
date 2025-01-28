package com.composetest.core.data.mappers

import com.composetest.core.data.api.responses.ApiSettingsResponse
import com.composetest.core.data.utils.decodeJson
import com.composetest.core.domain.models.ApiSettings
import javax.inject.Inject

internal class ApiSettingsMapper @Inject constructor() {

    operator fun invoke(json: String) = decodeJson<ApiSettingsResponse>(json).let {
        ApiSettings(apiKey = it.apiKey, url = it.url)
    }

    operator fun invoke(response: ApiSettingsResponse) =
        ApiSettings(apiKey = response.apiKey, url = response.url)
}