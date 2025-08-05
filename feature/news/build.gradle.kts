plugins {
    alias(libs.plugins.composetest.library)
    alias(libs.plugins.composetest.android.test)
    alias(libs.plugins.composetest.compose)
}

android {
    namespace = "com.composetest.feature.news"
}

dependencies {
    implementation(projects.common.api)
    implementation(projects.core.ui)
    implementation(projects.core.designsystem)
    implementation(projects.core.network)
    implementation(projects.core.database.androidapi)
    implementation(projects.core.data.api)
    implementation(projects.core.domain)
    implementation(projects.core.router)
    implementation(projects.core.analytic.api)
}