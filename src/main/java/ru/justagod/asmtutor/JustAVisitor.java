package ru.justagod.asmtutor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Created by JustAGod on 04.01.2018.
 */
public class JustAVisitor extends ClassVisitor {
    public JustAVisitor(ClassVisitor cv) {
        super(Opcodes.ASM5, cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        return new MethodVisitor(Opcodes.ASM5, super.visitMethod(access, name, desc, signature, exceptions)) {
            @Override
            public void visitCode() {
                super.visitFieldInsn(Opcodes.GETSTATIC, "cpw/mods/fml/common/Loader", "major", "I");
                super.visitInsn(Opcodes.IRETURN);
            }

            @Override
            public void visitInsn(int opcode) {
            }
        };
    }
}
