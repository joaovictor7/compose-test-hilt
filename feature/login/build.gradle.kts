import appconfig.AppModules
import utils.includeModules

plugins {
    alias(libs.plugins.composeTest.library)
    alias(libs.plugins.composeTest.compose)
    alias(libs.plugins.composeTest.test)
}

android {
    namespace = "com.composetest.feature.login"
}

dependencies {
    includeModules(
        AppModules.CORE,
        AppModules.CORE_TEST,
        AppModules.CORE_UI,
        AppModules.ROUTER
    )
    implementation(libs.firebase.auth)
}