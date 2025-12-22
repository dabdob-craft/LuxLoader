package net.lux.core;

import java.util.ArrayList;
import java.util.List;

public class ModManager {
    private static final List<ModMetadata> LOADED_MODS = new ArrayList<>();

    public static void loadAndInvoke(File modFile) {
        try {
            LuxClassLoader classLoader = new LuxClassLoader(ModManager.class.getClassLoader());
            classLoader.addModFolder(new File("mods"));
        
            String mainClassPath = "net.example.mod.ExampleMod"; 
            Class<?> modClass = Class.forName(mainClassPath, true, classLoader);
            modClass.getDeclaredMethod("onInitialize").invoke(modClass.getConstructor().newInstance());
        
        } catch (Exception e) {
            System.err.println("Failed to inject mod: " + modFile.getName());
        }
    }


    public static void addMod(ModMetadata mod) {
        LOADED_MODS.add(mod);
    }

    public static List<ModMetadata> getLoadedMods() {
        return LOADED_MODS;
    }

    public static int getModCount() {
        return LOADED_MODS.size();
    }
}
