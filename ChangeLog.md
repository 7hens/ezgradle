# Change Log

## TODO

- Migrate platform to version catalog
- Add `ezgradle.logLevel` to modify the log level
- Add Git properties to BuildConfig class
- Add `outdatedDependencies` task for project ezgradle-bom to show outdated dependencies
- Add `updateDependencies` task for project ezgradle-bom to update outdated dependencies automatically

## 2024

### 2024.03.01

- Feature: Experimental for version catalog
- Refactor: Generate BuildConfig.java instead of BuildConfig.kt

### 2024.02.02

- Feature: Update supabase-bom from 1.4.7 to 2.2.2

### 2024.02.01

- Feature: add `localProject` api
- Feature: update compose-bom to 2024.02.00
- Feature: add constraints: constriantlayout, autofill, robolectric

### 2024.01.01

- Add `BUILD_TIME` to BuildConfig class
- Support ksp plugin and ksp api 1.9.20-1.0.14

## 2023

## 2023.12.03

- Add `generateBuildConfig` task to pure Java projects
- Add hilt-compiler into `builtInLibs`
- Update Kotlin to 1.9.20
