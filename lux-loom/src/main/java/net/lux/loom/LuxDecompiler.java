package net.lux.loom;

import org.jetbrains.java.decompiler.main.extern.IFernflowerPreferences;
import org.jetbrains.java.decompiler.main.Fernflower;
import org.jetbrains.java.decompiler.main.decompiler.PrintStreamLogger;
import org.jetbrains.java.decompiler.main.extern.IBytecodeProvider;
import org.jetbrains.java.decompiler.main.extern.IResultSaver;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.nio.file.Files;

public class LuxDecompiler {
    public static void decompile(File inputJar, File outputDir) {
        System.out.println("[LuxDecompiler] Decompiling Minecraft... Please wait.");

        Map<String, Object> options = new HashMap<>();
        options.put(IFernflowerPreferences.DECOMPILE_GENERIC_SIGNATURES, "1");
        options.put(IFernflowerPreferences.INDENT_STRING, "    ");

        PrintStreamLogger logger = new PrintStreamLogger(System.out);
        
        IResultSaver saver = new LuxResultSaver(outputDir);
        IBytecodeProvider provider = (externalPath, internalPath) -> Files.readAllBytes(new File(externalPath).toPath());

        Fernflower engine = new Fernflower(provider, saver, options, logger);
        engine.addSource(inputJar);
        engine.decompileContext();

        System.out.println("[LuxDecompiler] Successfully decompiled to: " + outputDir.getPath());
    }
}
