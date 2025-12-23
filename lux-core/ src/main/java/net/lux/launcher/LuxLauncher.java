package net.lux.launcher;

import java.lang.instrument.Instrumentation;
import net.lux.core.ModManager;
import net.lux.api.event.LuxEvents;

public class LuxLauncher {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("====================================");
        System.out.println("     LUX LOADER: STARTING UP        ");
        System.out.println("            Just LUX.               ");
        System.out.println("====================================");

        inst.addTransformer(new LuxTransformer());
        
        ModManager.discoverMods();
        
        LuxEvents.ON_START.forEach(Runnable::run);
    }
}
