package com.composetest.core.ui.provider

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class StringResourceProviderImpl @Inject constructor(
    @param:ApplicationContext private val context: Context
) : StringResourceProvider {

    override fun getString(@StringRes stringId: Int, vararg args: Any): String {
        val arguments = getArguments(args)
        return context.getString(stringId, *arguments)
    }

    private fun getArguments(args: Array<out Any>) = mutableListOf<String>().apply {
        args.forEach {
            when (it) {
                is String -> add(it)
                is Int -> add(context.getString(it))
            }
        }
    }.toTypedArray()
}