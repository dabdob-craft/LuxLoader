package net.lux.loom;

import org.jetbrains.java.decompiler.main.extern.IFernflowerPreferences;
import org.jetbrains.java.decompiler.main.decompiler.ConsoleDecompiler;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class LuxDecompiler {
    public static void decompile(File inputJar, File outputDir) {
        System.out.println("[LuxDecompiler] Decompiling Minecraft... Please wait.");

        Map<String, Object> options = new HashMap<>();
        options.put(IFernflowerPreferences.DECOMPILE_GENERIC_SIGNATURES, "1");
        options.put(IFernflowerPreferences.INDENT_STRING, "    ");

        ConsoleDecompiler decompiler = new ConsoleDecompiler(outputDir, options);
        decompiler.addSource(inputJar);
        decompiler.decompileContext();

        System.out.println("[LuxDecompiler] Successfully decompiled to: " + outputDir.getPath());
    }
}
