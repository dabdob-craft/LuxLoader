package net.lux.launcher;

import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class LuxTransformer implements ClassFileTransformer {
    
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        
        if (className.equals("net/minecraft/client/gui/screens/TitleScreen") || 
            className.equals("net.minecraft.client.gui.screens.TitleScreen")) {
            return injectWatermark(classfileBuffer); 
        }
        return classfileBuffer;
    }

    private byte[] injectWatermark(byte[] basicClass) {
        ClassReader reader = new ClassReader(basicClass);
        ClassNode node = new ClassNode();
        reader.accept(node, 0);

        for (MethodNode method : node.methods) {
            if (method.name.equals("render") || method.name.equals("method_25394")) {
                InsnList insns = new InsnList();
                
                insns.add(new LdcInsnNode("LuxLoader 1.0.0"));
                insns.add(new IntInsnNode(Opcodes.BIPUSH, 2));
                insns.add(new IntInsnNode(Opcodes.BIPUSH, 2));
                insns.add(new IntInsnNode(Opcodes.SIPUSH, 0xFFFFFF));
                
            }
        }

        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        node.accept(writer);
        return writer.toByteArray();
    }
}
