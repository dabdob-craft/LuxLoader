package net.lux.loom;

import net.fabricmc.tinyremapper.OutputConsumerPath;
import net.fabricmc.tinyremapper.TinyRemapper;
import net.fabricmc.tinyremapper.TinyUtils;
import net.fabricmc.tinyremapper.IMappingProvider;
import java.io.File;
import java.nio.file.Path;

public class LuxRemapper {
    public static void remap(File inputJar, File outputJar, File mappingsFile) {
        System.out.println("[LuxRemapper] Starting Remap: " + inputJar.getName() + " -> " + outputJar.getName());

        TinyRemapper remapper = null;
        try (OutputConsumerPath outputConsumer = new OutputConsumerPath.Builder(outputJar.toPath()).build()) {
            
            IMappingProvider provider = TinyUtils.createTinyMappingProvider(mappingsFile.toPath(), "official", "named");

            remapper = TinyRemapper.newRemapper()
                    .withMappings(provider)
                    .build();

            remapper.readInputs(inputJar.toPath());
       
            remapper.apply(outputConsumer);
            
            System.out.println("[LuxRemapper] Remapping completed successfully.");
        } catch (Exception e) {
            System.err.println("[LuxRemapper] Error during remapping: " + e.getMessage());
        } finally {
            if (remapper != null) remapper.finish();
        }
    }
}
