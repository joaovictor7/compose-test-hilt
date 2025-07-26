plugins {
    alias(libs.plugins.composetest.library)
    alias(libs.plugins.composetest.compose)
    alias(libs.plugins.composetest.android.test)
}

android {
    namespace = "com.composetest.feature.configuration"
}

dependencies {
    implementation(projects.common)
    implementation(projects.core.ui)
    implementation(projects.core.designsystem)
    implementation(projects.core.database.androidapi)
    implementation(projects.core.data.androidapi)
    implementation(projects.core.domain)
    implementation(projects.core.security.api)
    implementation(projects.core.router)
    implementation(projects.core.analytic.api)
}