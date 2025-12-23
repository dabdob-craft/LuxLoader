package net.lux.loom;

import org.gradle.api.Project;
import java.net.URL;
import java.io.File;

public class MappingManager {
    public static void downloadMappings(Project project, String version) {
        System.out.println("[LuxLoom] Fetching mappings for Minecraft " + version);
        
        String url = "https://maven.fabricmc.net/net/fabricmc/intermediary/" + version + "/intermediary-" + version + "-v2.jar";
        
        try {
            File dest = new File(project.getBuildDir(), "lux/mappings.jar");
        } catch (Exception e) {
            project.getLogger().error("Failed to download mappings");
        }
    }
}
