package net.lux.core;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.lux.api.LuxMod;

import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarFile;

public class ModManager {

    public static void discoverMods() {
        File modsDir = new File("mods");
        if (!modsDir.exists()) modsDir.mkdirs();

        File[] files = modsDir.listFiles((dir, name) -> name.endsWith(".jar"));
        if (files == null) return;

        for (File file : files) {
            try (JarFile jar = new JarFile(file)) {
                var entry = jar.getEntry("lux.mod.json");
                if (entry != null) {
                    JsonObject json = JsonParser.parseReader(new InputStreamReader(jar.getInputStream(entry))).getAsJsonObject();
                    String mainClassPath = json.get("entrypoint").getAsString();

                    URLClassLoader loader = new URLClassLoader(
                        new URL[]{file.toURI().toURL()}, 
                        ModManager.class.getClassLoader()
                    );
                    
                    Class<?> modClass = Class.forName(mainClassPath, true, loader);

                    if (LuxMod.class.isAssignableFrom(modClass)) {
                        LuxMod modInstance = (LuxMod) modClass.getDeclaredConstructor().newInstance();
                        modInstance.onInitialize();
                        System.out.println("[Lux] Initialized mod: " + json.get("name").getAsString());
                    }
                }
            } catch (Exception e) {
                System.err.println("[Lux] Failed to load: " + file.getName());
            }
        }
    }
}
