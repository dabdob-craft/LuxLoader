package net.lux.core;

import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class LuxTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        
        if (className.equals("net/minecraft/client/gui/screens/TitleScreen")) {
            return modifyTitleScreen(classfileBuffer);
        }
        return classfileBuffer;
    }

    private byte[] modifyTitleScreen(byte[] basicClass) {
        ClassReader reader = new ClassReader(basicClass);
        ClassNode node = new ClassNode();
        reader.accept(node, 0);

        for (MethodNode method : node.methods) {
            if (method.name.equals("init") || method.name.equals("m_7856_")) { 
                System.out.println("[Lux] Injecting Button into TitleScreen!");
                
                InsnList instructions = new InsnList();
                
                method.instructions.insert(instructions);
            }
        }

        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        node.accept(writer);
        return writer.toByteArray();
    }
}
