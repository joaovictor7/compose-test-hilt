package com.composetest.common.providers

import java.util.Locale

interface LocaleProvider {
    val default: Locale
    val currentLanguage: String
}