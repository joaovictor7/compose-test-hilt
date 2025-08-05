package com.composetest.common.api.provider

import java.util.Locale

interface LocaleProvider {
    val default: Locale
    val currentLanguage: String
}