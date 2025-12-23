package net.lux.loom;

import org.gradle.api.Project;
import java.util.ArrayList;
import java.util.List;

public class LuxRunConfig {
    public static void setup(Project project) {
        List<String> args = new ArrayList<>();
        
        args.add("-javaagent:" + project.getRootDir() + "/lux-core/build/libs/lux-core-1.0.0.jar");
        
        args.add("net.minecraft.client.main.Main");

        System.out.println("[LuxLoom] Run configuration created with Agent support.");
    }
}
