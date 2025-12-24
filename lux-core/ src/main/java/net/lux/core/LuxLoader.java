package net.lux.core;

import com.google.gson.Gson;
import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class LuxLoader {
    private static final Gson GSON = new Gson();

    public static void init() {
        File modsDir = new File("mods");
        if (!modsDir.exists()) modsDir.mkdirs();

        File[] files = modsDir.listFiles((dir, name) -> name.endsWith(".jar"));
        if (files == null) return;

        for (File file : files) {
            try (JarFile jar = new JarFile(file)) {
                JarEntry entry = jar.getJarEntry("lux.mod.json");
                if (entry != null) {
                    InputStream is = jar.getInputStream(entry);
                    ModMetadata meta = GSON.fromJson(new InputStreamReader(is), ModMetadata.class);
                    
                    System.out.println("[Lux] Loading mod: " + meta.id + " v" + meta.version);

                    URLClassLoader child = new URLClassLoader(
                        new URL[]{file.toURI().toURL()},
                        LuxLoader.class.getClassLoader()
                    );

                    Class<?> modClass = Class.forName(meta.entrypoint, true, child);
                    modClass.getDeclaredConstructor().newInstance();
                    
                    System.out.println("[Lux] Mod " + meta.id + " initialized!");
                }
            } catch (Exception e) {
                System.err.println("[Lux] Failed to load mod " + file.getName() + ": " + e.getMessage());
            }
        }
    }
}
