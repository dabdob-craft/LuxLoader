package net.lux.loom;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class LuxLoomPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.getRepositories().mavenCentral();

        project.getDependencies().add("implementation", "net.lux:lux-api:1.0.0");

        project.getTasks().create("runLuxClient", task -> {
            task.setGroup("lux");
            task.setDescription("Run Minecraft with LuxLoader");

            task.doFirst(s -> {
                System.out.println("Starting Minecraft with LuxLoader Agent...");
            });
        });

        System.out.println("[LuxLoom] Plugin initialized successfully.");
    }
}
