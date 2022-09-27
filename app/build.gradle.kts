plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        applicationId = "pgm.poolp.ugbuilder"
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    buildFeatures {
        compose = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.1"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(Dependencies.coreAndroidX)

    implementation(Dependencies.uiCompose)
    implementation (Dependencies.materialCompose)
    implementation (Dependencies.uiToolingPreviewCompose)
    implementation(Dependencies.runtimeLiveData)
    implementation(Dependencies.iconsExtended)
    androidTestImplementation (Dependencies.uiTestJUnit4Compose)
    debugImplementation (Dependencies.uiToolingCompose)

    implementation (Dependencies.lifecycleRuntime)
    implementation (Dependencies.lifecycleLivedata)
    implementation (Dependencies.lifecycleViewModel)
    implementation (Dependencies.lifecycleViewModelCompose)
    implementation (Dependencies.activityCompose)
    testImplementation (Dependencies.junit)

    androidTestImplementation (Dependencies.junitAndroidX)
    debugImplementation (Dependencies.expressoAndroidX)

    implementation (Dependencies.hiltAndroid)
    kapt(Dependencies.hiltAndroidCompiler)

    implementation (Dependencies.hiltNavigationCompose)

    implementation (Dependencies.roomRuntine)
    implementation (Dependencies.roomKtx)
    annotationProcessor (Dependencies.roomCompiler)
    kapt(Dependencies.roomCompiler)

    implementation (Dependencies.accompanistInsets)
    implementation (Dependencies.coilCompose)

    implementation (Dependencies.appCompat)
    implementation (Dependencies.workRuntime)
    implementation (Dependencies.datastorePreferences)
    implementation (Dependencies.constraintLayoutCompose)
    implementation (Dependencies.GSon)
}
