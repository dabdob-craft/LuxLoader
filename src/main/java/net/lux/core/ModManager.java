package net.lux.core;

import java.util.ArrayList;
import java.util.List;

public class ModManager {
    private static final List<ModMetadata> DISCOVERED_MODS = new ArrayList<>();

    public static void addMod(ModMetadata mod) {
        DISCOVERED_MODS.add(mod);
    }

    public static List<ModMetadata> getMods() {
        return DISCOVERED_MODS;
    }

    public static int getModCount() {
        return DISCOVERED_MODS.size();
    }

}
