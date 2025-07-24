plugins {
    alias(libs.plugins.composetest.library)
    alias(libs.plugins.composetest.compose)
    alias(libs.plugins.composetest.android.test)
}

android {
    namespace = "com.composetest.feature.exchange"
}

dependencies {
    implementation(projects.common)
    implementation(projects.core.router)
    implementation(projects.core.designsystem)
    implementation(projects.core.network)
    implementation(projects.core.data.api)
    implementation(projects.core.domain)
    implementation(projects.core.ui)
    implementation(projects.core.analytic.api)
}