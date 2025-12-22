package net.lux.launcher;

import java.lang.instrument.Instrumentation;

public class LuxLauncher {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("###################################");
        System.out.println("   LuxLoader is starting STANDALONE");
        System.out.println("            Just LUX!              ");
        System.out.println("###################################");
        inst.addTransformer(new LuxTransformer());
    }
    public static void main(String[] args) {
        System.out.println("Please run this as a Java Agent via Minecraft Launcher.");
    }
}
