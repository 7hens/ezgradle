plugins {
    id("maven-publish")
    id("version-catalog")
}

catalog {
    versionCatalog {
        from(files("../gradle/libs.versions.toml"))
        version("ezgradle", "$version")
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            version = project.version.toString()
            from(components["versionCatalog"])
        }
    }
}

