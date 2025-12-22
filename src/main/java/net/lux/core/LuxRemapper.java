package net.lux.core;

import java.util.HashMap;
import java.util.Map;

public class LuxRemapper {
    private static final Map<String, String> classMappings = new HashMap<>();
    private static final Map<String, String> methodMappings = new HashMap<>();

    static {
        classMappings.put("net/minecraft/class_442", "net/minecraft/client/gui/screens/TitleScreen");
        methodMappings.put("method_2267", "init");
    }

    public static String mapMethod(String name) {
        return methodMappings.getOrDefault(name, name);
    }
    
    public static String mapClass(String className) {
        return classMappings.getOrDefault(className, className);
    }
}
