package com.example.catch_plugin;

import com.android.build.gradle.AppExtension;
import com.ss.android.ugc.bytex.common.BaseContext;

import org.gradle.api.Project;

public class Context extends BaseContext<ReportCatchExtension> {
    public Context(Project project, AppExtension android,
                   ReportCatchExtension extension) {
        super(project, android, extension);
    }
}
