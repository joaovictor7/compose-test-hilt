plugins {
    alias(libs.plugins.composeTest.library)
    alias(libs.plugins.composeTest.compose)
}

android {
    namespace = "com.composetest.core.ui"
}

dependencies {
    implementation(projects.common)
    implementation(projects.core.analytic)
    implementation(projects.core.router)
    implementation(libs.android.location)
    api(libs.android.permissions)
}