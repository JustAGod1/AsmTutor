package ru.justagod.asmtutor;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.util.Map;

/**
 * Created by JustAGod on 04.01.2018.
 */
@MCVersion(value = "1.7.10")
@SuppressWarnings("unused")
public class HookLoader implements IFMLLoadingPlugin, IClassTransformer {
    @Override
    public String[] getASMTransformerClass() {
        return new String[]{getClass().getName()};
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        switch (transformedName) {
            case "ru.justagod.asmtutor.Acessor": {
                ClassReader cr = new ClassReader(basicClass);
                ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
                JustAVisitor cv = new JustAVisitor(cw);
                cr.accept(cv, ClassReader.SKIP_FRAMES);
                return cw.toByteArray();
            }
            case "cpw.mods.fml.common.Loader": {
                ClassReader cr = new ClassReader(basicClass);
                ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
                JustALoaderVisitor cv = new JustALoaderVisitor(cw);
                cr.accept(cv, ClassReader.SKIP_FRAMES);
                return cw.toByteArray();
            }
        }
        return basicClass;
    }
}
