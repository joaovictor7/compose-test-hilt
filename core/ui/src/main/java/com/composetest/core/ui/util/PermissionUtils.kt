package com.composetest.core.ui.util

import androidx.compose.runtime.Composable
import com.composetest.core.ui.enums.Permission
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@Composable
@OptIn(ExperimentalPermissionsApi::class)
fun getMultiplePermissionState(
    permissions: List<Permission>,
    onPermissionResult: (Map<String, Boolean>) -> Unit = {}
) = rememberMultiplePermissionsState(
    permissions = permissions.map { it.manifest },
    onPermissionsResult = onPermissionResult
)
