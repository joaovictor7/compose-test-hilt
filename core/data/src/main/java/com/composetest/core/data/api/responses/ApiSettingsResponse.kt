package com.composetest.core.data.api.responses

import kotlinx.serialization.Serializable

@Serializable
internal data class ApiSettingsResponse(
    val apiKey: String,
    val url: String,
)