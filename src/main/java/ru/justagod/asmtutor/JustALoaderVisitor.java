package ru.justagod.asmtutor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Created by JustAGod on 04.01.2018.
 */
public class JustALoaderVisitor extends ClassVisitor {
    public JustALoaderVisitor(ClassVisitor cv) {
        super(Opcodes.ASM5, cv);
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        if (name.equals("major"))
            return super.visitField(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, name, desc, signature, value);
        else
            return super.visitField(access, name, desc, signature, value);
    }
}
