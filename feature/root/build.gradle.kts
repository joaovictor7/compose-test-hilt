plugins {
    alias(libs.plugins.composetest.android.library)
    alias(libs.plugins.composetest.android.test)
    alias(libs.plugins.composetest.compose)
}

android {
    namespace = "com.composetest.feature.root"
}

dependencies {
    implementation(projects.common.api)
    implementation(projects.core.router)
    implementation(projects.core.designsystem)
    implementation(projects.core.domain)
    implementation(projects.core.ui)
    implementation(projects.core.analytic.api)
}