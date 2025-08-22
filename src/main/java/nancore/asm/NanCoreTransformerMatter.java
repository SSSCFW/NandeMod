package nancore.asm;


import cpw.mods.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.*;
import net.minecraft.tileentity.TileEntityFurnace;

import static org.objectweb.asm.Opcodes.*;

public class NanCoreTransformerMatter implements IClassTransformer {
    String[] targetClasses = {
        "ic2.core.block.machine.tileentity.TileEntityMatter"
    };

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
                //対象クラス以外を除外する。対象は呼び出し元があるクラスである。
        if (targetClasses.length > 0 && !java.util.Arrays.asList(targetClasses).contains(transformedName)) {
            return bytes;
        }
        ClassReader cr = new ClassReader(bytes);
        ClassWriter cw = new ClassWriter(1);
        ClassVisitor cv = new ClassVisitor(ASM4, cw) {
            //クラス内のメソッドを訪れる。
            @Override
            public MethodVisitor visitMethod(int access, String methodName, String desc, String signature, String[] exceptions) {
                MethodVisitor mv = super.visitMethod(access, methodName, desc, signature, exceptions);
                //呼び出し元のメソッドを参照していることを確認する。
                String s1 = FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(name, methodName, desc);
                //C:\Users\<ユーザー名>\.gradle\caches\minecraft\net\minecraftforge\forge\1.7.10-10.13.4.1558-1.7.10\forge-1.7.10-10.13.4.1558-1.7.10-decomp.jar\より名称を検索、比較してメソッドの難読化名を探す。
                if (s1.equals("injectEnergy") || methodName.equals("injectEnergy")) {
                    mv = new MethodVisitor(ASM4, mv) {


                        @Override
                        public void visitJumpInsn(int opcode, Label label) {
                            // amount > 512.0 の分岐命令（IFLEやIFGTなど）を常にelse側にジャンプさせる
                            // ここではIFLE(<=)やIFGT(>)など、実際のバイトコードに合わせて判定
                            if (opcode == IFLE || opcode == IFGT) {
                                // 無条件ジャンプに書き換え
                                super.visitJumpInsn(GOTO, label);
                            } else {
                                super.visitJumpInsn(opcode, label);
                            }
                        }
                    };
                }
                return mv;
            }
        };
        cr.accept(cv, ClassReader.EXPAND_FRAMES);
        return cw.toByteArray();
    }
}
