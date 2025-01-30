package com.composetest.core.data.providers

import android.content.Context
import androidx.annotation.StringRes
import com.composetest.common.providers.StringResourceProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class StringResourceProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : StringResourceProvider {

    override fun getString(@StringRes stringId: Int, vararg args: Any): String {
        val arguments = getArguments(args.toList())
        return context.getString(stringId, *arguments)
    }

    private fun getArguments(args: List<Any>) = mutableListOf<String>().apply {
        args.forEach {
            when (it) {
                is String -> add(it)
                is Int -> add(context.getString(it))
            }
        }
    }.toTypedArray()
}