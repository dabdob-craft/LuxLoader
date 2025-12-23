package net.lux.core;

import java.lang.instrument.Instrumentation;

public class LuxAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("[LuxAgent] Injecting LuxLoader into the JVM...");
        
        LuxLoader.init();
      
    }
}
