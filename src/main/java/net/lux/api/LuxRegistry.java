package net.lux.api;

import java.util.ArrayList;
import java.util.List;

public class LuxRegistry {
    private static final List<LuxMod> MODS = new ArrayList<>();

    public static void registerMod(LuxMod mod) {
        MODS.add(mod);
        mod.onInitialize();
    }
}
