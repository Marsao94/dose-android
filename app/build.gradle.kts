plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.ksp)
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)
}

android {
    compileSdk = libs.versions.compile.sdk.version.get().toInt()

    defaultConfig {
        applicationId = "com.waseefakhtar.doseapp"
        minSdk = libs.versions.min.sdk.version.get().toInt()
        targetSdk = libs.versions.target.sdk.version.get().toInt()
        versionCode = libs.versions.version.code.get().toInt()
        versionName = libs.versions.version.name.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    androidResources {
        generateLocaleConfig = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        compose = true  // Abilita Jetpack Compose
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()  // Usa la versione definita nelle librerie
    }
    packaging {
        resources.excludes.apply {
            add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
    ksp {
        arg("room.schemaLocation", "$projectDir/schemas")
    }

    namespace = "com.waseefakhtar.doseapp"
}

dependencies {
    // BOM per gestire le versioni di Compose
    val composeBom = platform("androidx.compose:compose-bom:2024.09.00")
    implementation(composeBom)

    // Core AndroidX
    implementation(libs.androidx.core.ktx)

    // Jetpack Compose UI
    implementation("androidx.compose.ui:ui")                            // Core UI di Compose
    implementation("androidx.compose.material3:material3")              // Material Design 3
    implementation("androidx.navigation:navigation-compose")            // Navigazione Compose
    implementation("androidx.compose.ui:ui-tooling-preview")            // Preview di Compose
    implementation("androidx.activity:activity-compose")                // Activity per Compose

    // UI Tooling per debug e anteprime Compose
    debugImplementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.ui:ui-tooling-preview")

    // Lifecycle
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.viewmodel.compose)

    // Hilt per DI
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    kapt(libs.hilt.androidx.compiler)
    implementation(libs.hilt.navigation.compose)

    // Gson per la serializzazione JSON
    implementation(libs.gson)

    // Room per database
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    // OkHttp per networking
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)

    // Accompanist per funzionalità aggiuntive di Compose
    implementation(libs.accompanist.permission)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}
