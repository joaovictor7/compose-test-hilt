package com.composetest.common.provider

import java.util.Locale

interface LocaleProvider {
    val default: Locale
    val currentLanguage: String
}