package net.lux;

import net.lux.core.*;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.fabricmc.api.ModInitializer;
import java.io.*;
import java.nio.file.Paths;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;

public class LuxCore implements ModInitializer {
    public static final String MOD_ID = "lux";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    private static final Gson GSON = new Gson();

    @Override
    public void onInitialize() {
        ConfigManager.load();
        LOGGER.info("========================================");
        LOGGER.info("      LuxLoader: Initializing Engine    ");
        LOGGER.info("========================================");
        scanAndParseMods();
        LuxEvents.fireStartEvent("0.1.0-alpha");
    }

    private void scanAndParseMods() {
        File modsFolder = new File(Paths.get("").toAbsolutePath().toString(), "mods");
        if (!modsFolder.exists()) { modsFolder.mkdirs(); return; }

        File[] files = modsFolder.listFiles((dir, name) -> name.endsWith(".jar"));
        if (files == null) return;

        for (File file : files) {
            try (ZipFile zipFile = new ZipFile(file)) {
                ZipEntry luxEntry = zipFile.getEntry("lux.mod.json");
                ZipEntry fabricEntry = zipFile.getEntry("fabric.mod.json");
                ZipEntry targetEntry = (luxEntry != null) ? luxEntry : fabricEntry;

                if (targetEntry != null) {
                    try (Reader reader = new InputStreamReader(zipFile.getInputStream(targetEntry))) {
                        ModMetadata data = GSON.fromJson(reader, ModMetadata.class);
                        ModManager.discoverMods();
                        String type = (luxEntry != null) ? "Native" : "Fabric";
                        LOGGER.info("[Lux] {} Mod Loaded: {} ({})", type, data.name, data.version);
                    }
                }
            } catch (Exception e) {
                LOGGER.error("Failed to parse mod: " + file.getName());
            }
        }
    }
}

