package net.lux.core;

import java.net.URL;
import java.net.URLClassLoader;
import java.io.File;

public class LuxClassManager {
    public static void loadMod(File jarFile) throws Exception {
        URLClassLoader modClassLoader = new URLClassLoader(
            new URL[]{jarFile.toURI().toURL()},
            LuxClassManager.class.getClassLoader()
        );
        
        System.out.println("[Lux] Successfully injected classloader for: " + jarFile.getName());
    }
}
