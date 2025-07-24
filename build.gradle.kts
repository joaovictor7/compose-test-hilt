// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.composetest.application) apply false
    alias(libs.plugins.composetest.library) apply false
    alias(libs.plugins.composetest.compose) apply false
    alias(libs.plugins.composetest.test) apply false
    alias(libs.plugins.composetest.android.test) apply false
    alias(libs.plugins.composetest.kover) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false
//    alias(libs.plugins.screenshot) apply false // ~> revert after update
    alias(libs.plugins.kover) apply false
    alias(libs.plugins.kotlin.jvm) apply false
}