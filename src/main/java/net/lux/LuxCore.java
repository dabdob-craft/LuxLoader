package net.lux.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.fabricmc.api.ModInitializer;

public class LuxCore implements ModInitializer {
    public static final String MOD_ID = "lux";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("========================================");
        LOGGER.info("   Lux Loader is initializing...       ");
        LOGGER.info("   Developed by: dabdob-craft          ");
        LOGGER.info("   Status: High-Performance Mode ON    ");
        LOGGER.info("========================================");

    }
}
