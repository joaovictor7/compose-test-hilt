plugins {
    alias(libs.plugins.composetest.android.library)
    alias(libs.plugins.composetest.compose)
    alias(libs.plugins.composetest.android.test)
}

android {
    namespace = "com.composetest.feature.product"
}

dependencies {
    implementation(projects.common.api)
    implementation(projects.core.network)
    implementation(projects.core.router)
    implementation(projects.core.designsystem)
    implementation(projects.core.data.api)
    implementation(projects.core.domain)
    implementation(projects.core.database.androidapi)
    implementation(projects.core.ui)
    implementation(projects.core.analytic.api)
}