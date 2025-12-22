package net.lux.core;

import java.util.ArrayList;
import java.util.List;

public class ModManager {
    private static final List<ModMetadata> LOADED_MODS = new ArrayList<>();

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
