plugins {
    alias(libs.plugins.composetest.android.library)
    alias(libs.plugins.composetest.compose)
    alias(libs.plugins.composetest.android.test)
}

android {
    namespace = "com.composetest.feature.home"
}

dependencies {
    implementation(projects.core.router)
    implementation(projects.core.designsystem)
    implementation(projects.core.domain)
    implementation(projects.core.ui)
    implementation(projects.core.analytic.api)
}