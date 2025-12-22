package net.lux.core;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.network.chat.Component;

public class ModLoader implements ModInitializer {
    @Override
    public void onInitialize() {
        System.out.println("LuxLoader has been initialized!");
        
    }

    public static void showWelcomeToast(Minecraft client) {
        client.getToasts().addToast(new SystemToast(
            SystemToast.SystemToastIds.PERIODIC_NOTIFICATION,
            Component.literal("LuxLoader Active"),
            Component.literal("Loaded " + ModManager.getModCount() + " mods successfully!")
        ));
    }
}
