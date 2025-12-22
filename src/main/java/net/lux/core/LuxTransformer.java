package net.lux.core;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
import org.objectweb.asm.*;

public class LuxTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        
        if (className.equals("net/minecraft/client/gui/screens/TitleScreen")) {
            System.out.println("[Lux] Modifying TitleScreen...");
            return modifyBytecode(classfileBuffer);
        }
        return classfileBuffer;
    }
}
