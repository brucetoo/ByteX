package com.example.catch_plugin;

import com.ss.android.ugc.bytex.common.BaseContext;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.ArrayList;
import java.util.List;


public class ScanMethodVisitor extends MethodVisitor {

    private List<Label> tryCatchFirstLabels = new ArrayList<>();
    private BaseContext context;
    private boolean handleAddLog = false;

    public ScanMethodVisitor(int asmApi, MethodVisitor methodVisitor, BaseContext context) {
        super(asmApi, methodVisitor);
        this.context = context;
        tryCatchFirstLabels.clear();
    }

    @Override
    public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
        tryCatchFirstLabels.add(handler);
        context.getLogger().e("---Add try-catch Label:" + handler.toString() + "++++++ labelSize = " + tryCatchFirstLabels.size());
        super.visitTryCatchBlock(start, end, handler, type);
    }

    @Override
    public void visitLabel(Label label) {
        if(tryCatchFirstLabels.contains(label)) {
            context.getLogger().e("---Match visitLabel:" + label.toString());
            handleAddLog = true;
        }
        super.visitLabel(label);
    }

    @Override
    public void visitVarInsn(int opcode, int var) {
        super.visitVarInsn(opcode, var);
        if(opcode == Opcodes.ASTORE && var == 1 && handleAddLog) {
            context.getLogger().e("---ADD log~~~");
            mv.visitLdcInsn("CatchUtil");
            mv.visitLdcInsn("markCatch: out");
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "android/util/Log", "i", "(Ljava/lang/String;Ljava/lang/String;)I", false);
            mv.visitInsn(Opcodes.POP);
            handleAddLog = false;
        }
    }

    @Override
    public void visitEnd() {
        tryCatchFirstLabels.clear();
        super.visitEnd();
    }
}
