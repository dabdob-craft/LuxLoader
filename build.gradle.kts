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

    implementation("org.spongepowered:mixin:0.8.5")
}

tasks.withType<JavaCompile> {
    options.release.set(17)
}
