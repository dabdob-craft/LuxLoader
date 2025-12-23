package net.lux.loom;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class MinecraftDownloader {
    
    private static final String CLIENT_1_20_1_URL = "https://piston-data.mojang.com/v1/objects/84102660d170a4a82110c710f6993a4087b32247/client.jar";

    public static void downloadMinecraft(File targetFile) {
        if (targetFile.exists()) return;

        System.out.println("[LuxLoom] Downloading Minecraft 1.20.1 Client...");
        try (InputStream in = new URL(CLIENT_1_20_1_URL).openStream()) {
            targetFile.getParentFile().mkdirs();
            Files.copy(in, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("[LuxLoom] Download Complete: " + targetFile.getName());
        } catch (IOException e) {
            System.err.println("[LuxLoom] Failed to download Minecraft: " + e.getMessage());
        }
    }
}
