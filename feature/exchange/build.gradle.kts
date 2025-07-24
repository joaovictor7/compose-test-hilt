plugins {
    alias(libs.plugins.composeTest.library)
    alias(libs.plugins.composeTest.compose)
    alias(libs.plugins.composeTest.androidTest)
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