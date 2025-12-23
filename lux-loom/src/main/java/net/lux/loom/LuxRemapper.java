package net.lux.loom;

import net.fabricmc.tinyremapper.TinyRemapper;
import net.fabricmc.tinyremapper.TinyUtils;
import net.fabricmc.tinyremapper.IMappingProvider;
import java.io.File;
import java.nio.file.Path;

public class LuxRemapper {
    public static void remap(File inputJar, File outputJar, File mappingsFile) {
        System.out.println("[LuxRemapper] Mapping Minecraft to human-readable names...");

        try {
            IMappingProvider provider = TinyUtils.createTinyMappingProvider(mappingsFile.toPath(), "official", "named");

            TinyRemapper remapper = TinyRemapper.newRemapper()
                    .withMappings(provider)
                    .build();

            remapper.readInputs(inputJar.toPath());
            
            System.out.println("[LuxRemapper] Mapping finished!");
            remapper.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
