plugins {
    id("maven-publish")
    id("version-catalog")
}

catalog {
    versionCatalog {
        from(files("../gradle/libs.version.toml"))
    }
}
