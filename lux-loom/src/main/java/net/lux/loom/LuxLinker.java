package net.lux.loom;

import org.gradle.api.Project;

public class LuxLinker {
    public static void linkMinecraft(Project project, String version) {
        project.getRepositories().maven(repo -> {
            repo.setUrl("https://libraries.minecraft.net/");
        });
        
        System.out.println("[LuxLinker] Linking Minecraft version: " + version);
    }
}
