# EzGradle

[![](https://jitpack.io/v/7hens/ezgradle.svg)](https://jitpack.io/#7hens/ezgradle)

Upgrading old Android projects can be painful, especially for multi-module projects. And ezgradle is the solution.

## Setting up the dependency

**Step 1.** Add jitpack in your root settings.gradle.kts at the end of repositories:

```kotlin
// settings.gradle.kts
pluginManagement {
    repositories {
        maven("https://jitpack.io")
    }
}
dependencyResolutionManagement {
    repositories {
        maven("https://jitpack.io")
    }
}
```

**Step 2.** Add the dependency in your root build.gradle.kts.

```kotlin
// build.gradle.kts
plugins {
    id("com.gitlab.7hens.ezgradle") version "$latest_version" apply false
    // You don't need to add the commented plugins below, because ezgradle has already add them.
//    id("com.android.application") version "8.2.0" apply false
//    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
//    id("com.android.library") version "8.2.0" apply false
}
```

The latest version is [![](https://jitpack.io/v/7hens/ezgradle.svg)](https://jitpack.io/#7hens/ezgradle)

**Step 3.** Add the dependency in you module build.gradle.kts.

```kotlin
// build.gradle.kts
plugins {
    id("com.android.application")
    id("com.gitlab.7hens.ezgradle")
}
// With plugin ezgradle, you don't need to configure the android extension, there is default configuration for you.
// android {
// }
```

## References

- [Google Maven](https://maven.google.com/web/index.html)
- [Compose BOM](https://developer.android.com/jetpack/compose/bom/bom-mapping)
- [Compose Release](https://developer.android.com/jetpack/androidx/releases/compose-kotlin)
- [KSP Release](https://github.com/google/ksp/releases)
- [Jetpack Release](https://developer.android.com/jetpack/androidx/releases/activity)

