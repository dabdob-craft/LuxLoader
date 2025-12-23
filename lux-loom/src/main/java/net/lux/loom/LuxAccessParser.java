package net.lux.loom;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

public class LuxAccessParser {
    public void parse(File file) throws Exception {
        List<String> lines = Files.readAllLines(file.toPath());
        for (String line : lines) {
            if (line.startsWith("unhide")) {
                String target = line.split(" ")[1];
                System.out.println("[LuxAccess] Target found to unhide: " + target);
            }
        }
    }
}
