# EzGradle

[![](https://jitpack.io/v/com.gitlab.7hens/ezgradle.svg)](https://jitpack.io/#com.gitlab.7hens/ezgradle)

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

plugins {
    id("com.gitlab.7hens.ezgradle") version "2024.03.01"
}
```

**Step 2.** Add the dependency in your root build.gradle.kts.

```kotlin
// build.gradle.kts
allprojects {
    apply(plugin = "com.gitlab.7hens.ezgradle.project")
}
```

The latest version is [![](https://jitpack.io/v/7hens/ezgradle.svg)](https://jitpack.io/#7hens/ezgradle)

## References

- [Google Maven](https://maven.google.com/web/index.html)
- [Compose BOM](https://developer.android.com/jetpack/compose/bom/bom-mapping)
- [Compose Release](https://developer.android.com/jetpack/androidx/releases/compose-kotlin)
- [KSP Release](https://github.com/google/ksp/releases)
- [Jetpack Release](https://developer.android.com/jetpack/androidx/releases/activity)

