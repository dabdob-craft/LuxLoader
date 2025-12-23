package net.lux.loom;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import java.io.File;

public class LuxLoomPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.getRepositories().mavenCentral();
        project.getDependencies().add("implementation", "net.lux:lux-api:1.0.0");

        project.getTasks().create("processAccessWidener", task -> {
            task.setGroup("lux");
            task.doLast(s -> {
                File awFile = project.file("src/main/resources/lux.accesswidener");
                if (awFile.exists()) {
                    System.out.println("[LuxLoom] Found Access Widener file, applying transformations...");
                }
            });
        });

        project.getTasks().create("runLuxClient", task -> {
            task.setGroup("lux");
            task.doFirst(s -> {
                System.out.println("Starting Minecraft with LuxLoader...");
            });
        });
    }
}
