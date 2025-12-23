package net.lux.loom;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import java.io.File;

public class LuxLoomPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        LuxLinker.linkMinecraft(project, "1.20.1");
        LuxRunConfig.setup(project);
        project.getRepositories().mavenCentral();
        project.getDependencies().add("implementation", "net.lux:lux-api:1.0.0"); 
        System.out.println("[LuxLoom] Total System Integrated.");
        project.getTasks().create("processAccessWidener", task -> {
            task.setGroup("lux");
            task.doLast(s -> {
                File awFile = project.file("src/main/resources/lux.accesswidener");
                if (awFile.exists()) {
                    System.out.println("[LuxLoom] Found Access Widener file, applying transformations...");
                }
            });
        });

        project.getTasks().create("genSources", task -> {
    task.setGroup("lux");
    task.setDescription("Decompiles Minecraft and maps it for development.");
    
    task.doLast(s -> {
        File minecraftJar = new File(project.getBuildDir(), "lux/minecraft-mapped.jar");
        File sourcesOutput = new File(project.getProjectDir(), "src/generated/java");
        
        if (minecraftJar.exists()) {
            LuxDecompiler.decompile(minecraftJar, sourcesOutput);
        } else {
            System.err.println("[LuxLoom] Error: Mapped Minecraft JAR not found! Run 'mapMinecraft' first.");
                }
            });
        });

    }
}
