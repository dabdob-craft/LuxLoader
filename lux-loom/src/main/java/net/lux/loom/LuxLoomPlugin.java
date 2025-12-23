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
    task.doLast(s -> {
        File rawJar = new File(project.getBuildDir(), "lux/minecraft-raw.jar");
        File mappedJar = new File(project.getBuildDir(), "lux/minecraft-mapped.jar");
        File mappings = new File(project.getProjectDir(), "mappings/mappings.tiny");
        File sourcesOutput = new File(project.getProjectDir(), "src/generated/java");

        LuxRemapper.remap(rawJar, mappedJar, mappings);

        LuxDecompiler.decompile(mappedJar, sourcesOutput);
        
        System.out.println(">>> LuxLoader: Sources are ready for development!");
            });
        });
        
    }
}
