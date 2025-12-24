package net.lux.core;

import com.google.gson.Gson;
import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class LuxLoader {
    private static final Gson GSON = new Gson();
    private static final List<Object> LOADED_MODS = new ArrayList<>();

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
                    
                    System.out.println("[Lux] Loading mod: " + meta.id);

                    URLClassLoader child = new URLClassLoader(
                        new URL[]{file.toURI().toURL()},
                        LuxLoader.class.getClassLoader()
                    );

                    Class<?> modClass = Class.forName(meta.entrypoint, true, chil
                    Object modInstance = modClass.getDeclaredConstructor().newInstance();
                    LOADED_MODS.add(modInstance);
                    
                    System.out.println("[Lux] Mod " + meta.id + " initialized!");
                }
            } catch (Exception e) {
                System.err.println("[Lux] Failed to load mod " + file.getName() + ": " + e.getMessage());
            }
        }
    }

    public static void onTick() {
    }
}
