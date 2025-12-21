package net.lux.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.fabricmc.api.ModInitializer;
import java.io.File;
import java.nio.file.Paths;

public class LuxCore implements ModInitializer {
    public static final String MOD_ID = "lux";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("========================================");
        LOGGER.info("        LuxLoader Core Loading          ");
        LOGGER.info("========================================");

        discoverMods();
    }

    private void discoverMods() {
        File modsFolder = new File(Paths.get("").toAbsolutePath().toString(), "mods");
        
        if (modsFolder.exists() && modsFolder.isDirectory()) {
            File[] files = modsFolder.listFiles((dir, name) -> name.endsWith(".jar"));
            
            if (files != null) {
                LOGGER.info("LuxLoader found {} potential mods.", files.length);
                for (File file : files) {
                    LOGGER.info("-> Discovered mod: {}", file.getName());
                }
            }
        } else {
            LOGGER.warn("Mods folder not found at: {}", modsFolder.getAbsolutePath());
        }
    }
}
