plugins {
    alias(libs.plugins.composetest.library)
    alias(libs.plugins.composetest.compose)
}

android {
    namespace = "com.composetest.core.ui"
}

dependencies {
    implementation(projects.common)
    implementation(projects.core.analytic.api)
    implementation(projects.core.router)
    implementation(libs.android.location)
    api(libs.android.permissions)
}