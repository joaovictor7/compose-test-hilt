package com.composetest.core.data.impl.provider

import com.composetest.common.api.provider.LocaleProvider
import java.util.Locale
import javax.inject.Inject

internal class LocaleProviderImpl @Inject constructor() : LocaleProvider {
    override val default: Locale get() = Locale.getDefault()
    override val currentLanguage: String get() = Locale.getDefault().language
}