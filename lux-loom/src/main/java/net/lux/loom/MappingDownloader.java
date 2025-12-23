package net.lux.loom;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.io.File;

public class MappingDownloader {
    public static File download(String version, File destination) {
        System.out.println("[LuxLoom] Fetching mappings for version: " + version);
        String url = "https://maven.fabricmc.net/net/fabricmc/yarn/" + version + "/yarn-" + version + "-tiny.gz";
        
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(destination)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            System.out.println("[LuxLoom] Mappings downloaded successfully.");
            return destination;
        } catch (Exception e) {
            System.err.println("[LuxLoom] Failed to download mappings: " + e.getMessage());
            return null;
        }
    }
}
