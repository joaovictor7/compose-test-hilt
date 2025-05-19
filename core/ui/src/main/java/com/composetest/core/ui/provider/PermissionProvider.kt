package com.composetest.core.ui.provider

import com.composetest.core.ui.enums.Permission

interface PermissionProvider {
    fun permissionIsGranted(vararg permission: Permission): Boolean
    fun somePermissionIsGranted(vararg permission: Permission): Boolean
    fun permissionIsGranted(permission: List<Permission>): Boolean
    fun somePermissionIsGranted(permission: List<Permission>): Boolean
}