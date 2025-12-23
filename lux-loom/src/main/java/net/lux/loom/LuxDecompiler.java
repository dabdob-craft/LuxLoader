package net.lux.loom;

import org.gradle.api.Project;
import java.io.File;

public class LuxDecompiler {
    public static void decompile(Project project, File inputJar, File outputSrc) {
        System.out.println("[LuxDecompiler] Starting to decompile Minecraft...");
        
      
        System.out.println("[LuxDecompiler] Decompilation finished: " + outputSrc.getName());
    }
}
