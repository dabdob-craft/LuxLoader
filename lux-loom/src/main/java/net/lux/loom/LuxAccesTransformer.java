package net.lux.loom;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

public class LuxAccessTransformer extends ClassVisitor {

    public LuxAccessTransformer(ClassVisitor cv) {
        super(Opcodes.ASM9, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        int newAccess = transformAccess(access);
        super.visit(version, newAccess, name, signature, superName, interfaces);
    }

    @Override
    public org.objectweb.asm.MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        int newAccess = transformAccess(access);
        return super.visitMethod(newAccess, name, descriptor, signature, exceptions);
    }

    private int transformAccess(int access) {
        int acc = access & ~Opcodes.ACC_PRIVATE & ~Opcodes.ACC_PROTECTED | Opcodes.ACC_PUBLIC;
        return acc & ~Opcodes.ACC_FINAL;
    }
}
