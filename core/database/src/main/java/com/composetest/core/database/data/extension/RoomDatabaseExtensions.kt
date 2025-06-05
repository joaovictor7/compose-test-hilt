package com.composetest.core.database.data.extension

import android.util.Log
import androidx.room.RoomDatabase
import com.composetest.core.domain.model.buildconfig.BuildConfigModel
import java.util.concurrent.Executors

internal fun <T : RoomDatabase> RoomDatabase.Builder<T>.addLogs(
    buildConfig: BuildConfigModel,
) = also {
    if (buildConfig.isRelease) return@also
    setQueryCallback({ sqlQuery, bindArgs ->
        Log.i("SQLite", "SQL Query: $sqlQuery")
        if (bindArgs.isNotEmpty()) Log.i("SQLite", "SQL Args: $bindArgs")
    }, Executors.newSingleThreadExecutor())
}