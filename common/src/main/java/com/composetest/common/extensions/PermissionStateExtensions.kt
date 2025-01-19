@file:OptIn(ExperimentalPermissionsApi::class)

package com.composetest.common.extensions

import com.composetest.common.enums.Permission
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.PermissionStatus

fun MultiplePermissionsState.hasAnyPermissionsGranted(vararg requiredPermissions: Permission): Boolean {
    val requiredPermissionSet = requiredPermissions.flatMap { it.manifest }.toSet()
    val grantedPermissions = permissions.filter {
        it.status == PermissionStatus.Granted
    }.map { it.permission }.toSet()
    return requiredPermissionSet.any { it in grantedPermissions }
}