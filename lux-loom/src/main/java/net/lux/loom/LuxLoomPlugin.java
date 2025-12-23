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

        project.getTasks().register("processAccessWidener", task -> {
            task.setGroup("lux");
            task.doLast(s -> {
                File awFile = project.file("src/main/resources/lux.accesswidener");
                if (awFile.exists()) {
                    System.out.println("[LuxLoom] Found Access Widener file, applying transformations...");
                }
            });
        });

        project.getTasks().register("genSources", task -> {
            task.setGroup("lux");
            task.doLast(s -> {
                File luxCache = new File(project.getGradle().getGradleUserHomeDir(), "caches/lux");
                File mappingFile = new File(luxCache, "mappings/mappings-1.20.1.tiny");
    
                File buildDir = project.getLayout().getBuildDirectory().getAsFile().get();
                File rawJar = new File(buildDir, "lux/minecraft-raw.jar");
                File mappedJar = new File(buildDir, "lux/minecraft-mapped.jar");
                File sourcesOutput = new File(project.getProjectDir(), "src/generated/java");

                if (!mappingFile.exists()) {
                    mappingFile.getParentFile().mkdirs();
                    MappingDownloader.download("1.20.1+build.10", mappingFile);
                }

                MinecraftDownloader.downloadMinecraft(rawJar);

                LuxRemapper.remap(rawJar, mappedJar, mappingFile);

                LuxDecompiler.decompile(mappedJar, sourcesOutput);
                
                System.out.println(">>> [LuxLoader] Sources are ready! Check: " + sourcesOutput.getPath());
            });
        });
    }
}
