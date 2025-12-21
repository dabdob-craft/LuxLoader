package net.lux.core;

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
        LOGGER.info("========================================");
        LOGGER.info("      LuxLoader: Analyzing Metadata     ");
        LOGGER.info("========================================");
        scanAndParseMods();
    }

    private void scanAndParseMods() {
        File modsFolder = new File(Paths.get("").toAbsolutePath().toString(), "mods");
        if (!modsFolder.exists()) { modsFolder.mkdirs(); return; }

        File[] files = modsFolder.listFiles((dir, name) -> name.endsWith(".jar"));
        if (files == null) return;

        for (File file : files) {
            try (ZipFile zipFile = new ZipFile(file)) {
                ZipEntry entry = zipFile.getEntry("lux.mod.json");
                if (entry != null) {
                    try (Reader reader = new InputStreamReader(zipFile.getInputStream(entry))) {
                        ModMetadata data = GSON.fromJson(reader, ModMetadata.class);
                        LOGGER.info("[Lux] Loaded: {} ({}) by {}", data.name, data.version, data.author);
                    }
                }
            } catch (Exception e) {
                LOGGER.error("Failed to parse mod: " + file.getName());
            }
        }
    }
}
