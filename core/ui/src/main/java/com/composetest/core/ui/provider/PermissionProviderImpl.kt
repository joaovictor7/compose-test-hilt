package com.composetest.core.ui.provider

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.composetest.core.ui.enums.Permission
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class PermissionProviderImpl @Inject constructor(
    @param:ApplicationContext private val context: Context,
) : PermissionProvider {

    override fun permissionIsGranted(vararg permission: Permission) =
        permissionIsGranted(permission.toList())

    override fun somePermissionIsGranted(vararg permission: Permission) =
        somePermissionIsGranted(permission.toList())

    override fun permissionIsGranted(permission: List<Permission>) = permission.all {
        it.checkPermission()
    }

    override fun somePermissionIsGranted(permission: List<Permission>) = permission.any {
        it.checkPermission()
    }

    private fun Permission.checkPermission(): Boolean =
        ContextCompat.checkSelfPermission(context, manifest) == PackageManager.PERMISSION_GRANTED
}