package net.lux.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LuxLoader {
    private static final File MODS_DIR = new File("mods");

    public static void init() {
        System.out.println("[LuxLoader-Core] Searching for mods in " + MODS_DIR.getAbsolutePath());

        if (!MODS_DIR.exists()) {
            MODS_DIR.mkdirs();
            return;
        }

        File[] mods = MODS_DIR.listFiles((dir, name) -> name.endsWith(".jar"));
        if (mods != null) {
            for (File mod : mods) {
                System.out.println("[LuxLoader] Found mod: " + mod.getName());
            }
        }
    }
}
