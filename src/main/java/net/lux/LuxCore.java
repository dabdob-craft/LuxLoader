package net.lux.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.fabricmc.api.ModInitializer;
import java.io.File;
import java.nio.file.Paths;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class LuxCore implements ModInitializer {
    public static final String MOD_ID = "lux";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("========================================");
        LOGGER.info("      LuxLoader: Initializing Engine    ");
        LOGGER.info("========================================");

        scanAndParseMods();
    }

    private void scanAndParseMods() {
        File modsFolder = new File(Paths.get("").toAbsolutePath().toString(), "mods");
        
        if (!modsFolder.exists()) {
            modsFolder.mkdirs();
            return;
        }

        File[] files = modsFolder.listFiles((dir, name) -> name.endsWith(".jar"));
        
        if (files != null) {
            for (File file : files) {
                try (ZipFile zipFile = new ZipFile(file)) {
                    ZipEntry entry = zipFile.getEntry("lux.mod.json");
                    if (entry != null) {
                        LOGGER.info("[Lux] Found Lux-compatible mod: {}", file.getName());
                    } else if (zipFile.getEntry("fabric.mod.json") != null) {
                        LOGGER.info("[Lux] Found Fabric-compatible mod: {}", file.getName());
                    }
                } catch (Exception e) {
                    LOGGER.error("Failed to read mod file: " + file.getName(), e);
                }
            }
        }
    }
}
