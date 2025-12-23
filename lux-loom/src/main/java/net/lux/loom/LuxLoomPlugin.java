package net.lux.loom;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import java.io.File;

public class LuxLoomPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        System.out.println("-----------------------------------------");
        System.out.println("   _      _    _  __   __               ");
        System.out.println("  | |    | |  | | \\ \\ / /               ");
        System.out.println("  | |    | |  | |  \\ V /                ");
        System.out.println("  | |    | |  | |   > <                 ");
        System.out.println("  | |____| |__| |  / ^ \\                ");
        System.out.println("  |______|\\____/  /_/ \\_\\  LOADER       ");
        System.out.println("   Integrated & Independent System      ");
        System.out.println("-----------------------------------------");

        LuxLinker.linkMinecraft(project, "1.20.1");
        LuxRunConfig.setup(project);
        
        project.getRepositories().mavenCentral();
        project.getDependencies().add("implementation", "net.lux:lux-api:1.0.0"); 
        
        System.out.println("[LuxLoom] Total System Integrated.");

        project.getTasks().register("processLuxAccess", task -> {
            task.setGroup("lux");
            task.doLast(s -> {
                File accessFile = project.file("src/main/resources/lux.access");
        
                if (accessFile.exists()) {
                    System.out.println("[LuxLoom] Found Lux Access file! Starting independent transformation...");
            
                    File jarToModify = new File(project.getLayout().getBuildDirectory().getAsFile().get(), "lux/minecraft-mapped.jar");
            
                    LuxAccessProcessor.apply(jarToModify, accessFile);
                } else {
                    System.out.println("[LuxLoom] No lux.access file found. Skipping access widening.");
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
