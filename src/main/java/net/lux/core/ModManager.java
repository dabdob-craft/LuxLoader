package net.lux.core;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

public class ModManager {
    private static final List<ModMetadata> loadedMods = new ArrayList<>();
    private static final File modsDir = new File("mods");

    public static void discoverMods() {
        if (!modsDir.exists()) modsDir.mkdirs();

        File[] files = modsDir.listFiles((dir, name) -> name.endsWith(".jar"));
        if (files == null) return;

        for (File file : files) {
            try (JarFile jar = new JarFile(file)) {
                ZipEntry entry = jar.getEntry("lux.mod.json");
                if (entry != null) {
                    JsonObject json = JsonParser.parseReader(new InputStreamReader(jar.getInputStream(entry))).getAsJsonObject();
                    
                    String id = json.get("id").getAsString();
                    String name = json.get("name").getAsString();
                    String version = json.get("version").getAsString();
                    String description = json.get("description").getAsString();
                    String author = json.get("author").getAsString();
                    String mainClass = json.get("entrypoint").getAsString();

                    ModMetadata meta = new ModMetadata(id, name, version, description, author);
                    loadedMods.add(meta);
                    
                    loadAndInvoke(file, mainClass);
                }
            } catch (Exception e) {
                System.err.println("Failed to read mod file: " + file.getName());
            }
        }
    }

    private static void loadAndInvoke(File modFile, String mainClassPath) {
        try {
            LuxClassLoader classLoader = new LuxClassLoader(ModManager.class.getClassLoader());
            classLoader.addModFile(modFile);
            
            Class<?> modClass = Class.forName(mainClassPath, true, classLoader);
            Object modInstance = modClass.getDeclaredConstructor().newInstance();
            modClass.getMethod("onInitialize").invoke(modInstance);
            
            System.out.println("[LuxLoader] Successfully initialized: " + mainClassPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<ModMetadata> getLoadedMods() { return loadedMods; }
    public static int getModCount() { return loadedMods.size(); }
}
