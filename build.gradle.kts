plugins {
    java
    id("dev.architectury.loom") version "1.3.383" 
    id("maven-publish")
}

group = "net.lux"
version = "0.1.0-alpha"

repositories {
    mavenCentral()
    maven("https://maven.fabricmc.net/")
}

dependencies {
    minecraft("com.mojang:minecraft:1.20.1") 
    
    mappings(loom.officialMojangMappings()) 

    implementation("org.spongepowered:mixin:0.8.5")
}

tasks.withType<JavaCompile> {
    options.release.set(17)
}
