package net.lux.loom;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.util.Collections;
import java.util.List;

public class LuxAccessProcessor {
    public static void apply(File jarFile, File accessFile) {
        System.out.println("[LuxAccess] Processing JAR: " + jarFile.getName());

        URI jarUri = URI.create("jar:" + jarFile.toURI());
        
        try (FileSystem zipfs = FileSystems.newFileSystem(jarUri, Collections.emptyMap())) {
            List<String> lines = Files.readAllLines(accessFile.toPath());

            for (String line : lines) {
                if (line.startsWith("open-class")) {
                    String className = line.split(" ")[1].replace(".", "/") + ".class";
                    Path classPath = zipfs.getPath("/" + className);

                    if (Files.exists(classPath)) {
                        byte[] originalBytes = Files.readAllBytes(classPath);
                        byte[] modifiedBytes = transform(originalBytes);
                        Files.write(classPath, modifiedBytes, StandardOpenOption.TRUNCATE_EXISTING);
                        System.out.println("[LuxAccess] Opened class: " + className);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("[LuxAccess] Error modifying JAR: " + e.getMessage());
        }
    }

    private static byte[] transform(byte[] bytes) {
        ClassReader reader = new ClassReader(bytes);
        ClassWriter writer = new ClassWriter(reader, 0);
        LuxAccessTransformer transformer = new LuxAccessTransformer(writer);
        reader.accept(transformer, 0);
        return writer.toByteArray();
    }
}
