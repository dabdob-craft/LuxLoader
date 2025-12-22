package net.lux.core;

import net.lux.LuxCore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;

public class ConfigManager {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File CONFIG_FILE = new File("config/lux_config.json");
    public static LuxConfig config = new LuxConfig();

    public static void load() {
        if (!CONFIG_FILE.exists()) {
            save();
            return;
        }
        try (Reader reader = new FileReader(CONFIG_FILE)) {
            config = GSON.fromJson(reader, LuxConfig.class);
        } catch (IOException e) {
            LuxCore.LOGGER.error("Could not load Lux config!");
        }
    }

    public static void save() {
        CONFIG_FILE.getParentFile().mkdirs();
        try (Writer writer = new FileWriter(CONFIG_FILE)) {
            GSON.toJson(config, writer);
        } catch (IOException e) {
            LuxCore.LOGGER.error("Could not save Lux config!");
        }
    }
}
