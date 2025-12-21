plugins {
    java
    id("fabric-loom") version "1.4-SNAPSHOT"
    id("maven-publish")
}

group = "net.lux"
version = "0.1.0-alpha"

repositories {
    mavenCentral()
    maven("https://maven.fabricmc.net/")
    maven("https://repo.spongepowered.org/repository/maven-public/") 
}

dependencies {
    minecraft("com.mojang:minecraft:1.20.1")
    mappings(loom.officialMojangMappings())

    modImplementation("net.fabricmc:fabric-loader:0.15.7")

    implementation("org.spongepowered:mixin:0.8.5")
    implementation("com.google.code.gson:gson:2.10.1")
}

tasks.withType<JavaCompile> {
    options.release.set(17)
}

loom {
    accessWidenerPath.set(file("src/main/resources/lux.accesswidener"))
}

tasks.jar {
    manifest {
        attributes(
            "Main-Class" to "net.lux.core.LuxCore",
            "Implementation-Title" to "LuxLoader",
            "Implementation-Version" to project.version,
            "Implementation-Vendor" to "dabdob-craft",
            "Prerelease" to "true",
            "Multi-Release" to true,
            "Build-Jdk-Spec" to "17" 
        )
    }
}
