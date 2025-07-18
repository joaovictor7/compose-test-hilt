plugins {
    alias(libs.plugins.composeTest.library)
    alias(libs.plugins.composeTest.androidTest)
    alias(libs.plugins.composeTest.compose)
}

android {
    namespace = "com.composetest.feature.news"
}

dependencies {
    implementation(projects.common)
    implementation(projects.core.ui)
    implementation(projects.core.designsystem)
    implementation(projects.core.network)
    implementation(projects.core.database)
    implementation(projects.core.data.api)
    implementation(projects.core.domain)
    implementation(projects.core.router)
    implementation(projects.core.analytic)
}