// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.composeTest.application) apply false
    alias(libs.plugins.composeTest.library) apply false
    alias(libs.plugins.composeTest.compose) apply false
    alias(libs.plugins.composeTest.test) apply false
    alias(libs.plugins.composeTest.kover) apply true
    alias(libs.plugins.kotlinSerialization) apply false
    alias(libs.plugins.googleServices) apply false
    alias(libs.plugins.firebaseCrashlytics) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.screenshot) apply false
    alias(libs.plugins.kover) apply false
}