package net.lux.loom;

import org.jetbrains.java.decompiler.main.extern.IResultSaver;
import java.io.File;
import java.nio.file.Files;
import java.util.jar.Manifest;

public class LuxResultSaver implements IResultSaver {
    private final File outputDir;

    public LuxResultSaver(File outputDir) {
        this.outputDir = outputDir;
        if (!outputDir.exists()) outputDir.mkdirs();
    }

    @Override
    public void saveClassFile(String path, String clsName, String entryName, String content, int[] mapping) {
        try {
            File out = new File(outputDir, entryName + ".java");
            out.getParentFile().mkdirs();
            Files.writeString(out.toPath(), content);
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override public void saveFolder(String path) {}
    @Override public void copyFile(String source, String path, String entryName) {}
    @Override public void createArchive(String path, String archiveName, Manifest manifest) {}
    @Override public void saveDirEntry(String path, String archiveName, String entryName) {}
    @Override public void copyEntry(String source, String path, String archiveName, String entryName) {}
    @Override public void saveClassEntry(String path, String archiveName, String clsName, String entryName, String content) {}
    @Override public void closeArchive(String path, String archiveName) {}
}
