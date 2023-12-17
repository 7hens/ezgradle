# EzGradle

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
    id("com.github.7hens.ezgradle") version "2023.12.01" apply false
    // You don't need to add the commented plugins below, because ezgradle has already add them.
//    id("com.android.application") version "8.2.0" apply false
//    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
//    id("com.android.library") version "8.2.0" apply false
}
```

**Step 3.** Add the dependency in you module build.gradle.kts.

```kotlin
// build.gradle.kts
plugins {
    id("com.android.application")
    id("com.github.7hens.ezgradle")
}
// With plugin ezgradle, you don't need to configure the android extension, there is default configuration for you.
// android {
// }
```