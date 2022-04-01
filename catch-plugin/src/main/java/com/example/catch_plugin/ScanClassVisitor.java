package com.example.catch_plugin;

import com.ss.android.ugc.bytex.common.BaseContext;
import com.ss.android.ugc.bytex.common.Constants;
import com.ss.android.ugc.bytex.common.visitor.BaseClassVisitor;

import org.objectweb.asm.MethodVisitor;

public class ScanClassVisitor extends BaseClassVisitor {

    private BaseContext context;
    public ScanClassVisitor(BaseContext context) {
        this.context = context;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        // 这里可以通过name来做黑白名单的校验啥的
        // 目前这里啥也没做，表示对所有的class生效
        if(name.contains("markCatch")) {
            this.context.getLogger().e("match markCatch!!!!-----");
            MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);
            return new ScanMethodVisitor(Constants.ASM_API, methodVisitor, this.context);
        }
        return super.visitMethod(access, name, descriptor, signature, exceptions);

    }
}
