package com.example.catch_plugin;

import android.util.Log;

import com.android.build.gradle.AppExtension;
import com.ss.android.ugc.bytex.common.BaseContext;
import com.ss.android.ugc.bytex.common.CommonPlugin;
import com.ss.android.ugc.bytex.common.visitor.ClassVisitorChain;
import com.ss.android.ugc.bytex.pluginconfig.anno.PluginConfig;

import org.gradle.api.Project;

import javax.annotation.Nonnull;

public class ReportCatchPlugin extends CommonPlugin<ReportCatchExtension, Context> {

    @Override
    protected Context getContext(Project project, AppExtension android, ReportCatchExtension extension) {
        return new Context(project, android, extension);
    }

    public boolean transform(@Nonnull String relativePath, @Nonnull ClassVisitorChain chain) {
        chain.connect(new ScanClassVisitor(context));
        return super.transform(relativePath, chain);
    }
}