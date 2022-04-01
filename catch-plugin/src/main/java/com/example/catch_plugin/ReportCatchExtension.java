package com.example.catch_plugin;

import com.ss.android.ugc.bytex.common.BaseExtension;

/**
 * 用于处理gradle插件的配置文件的，比如baseExtension里面有 enable和enableInDebug 的配置
 * 对应的使用方式为
 * apply plugin: 'bytex' // 插件基础配置引入
 * apply plugin: 'bytex.report_catch' // 这个名字是在 resource里面定义的，与getName呼应
 * report_catch { // 名字就是 getName对应
 *     ..... 对应的配置
 * }
 * // https://blog.csdn.net/ouyang_peng/article/details/114368530 这个文章有关于这个的配置参考说明
 */
public class ReportCatchExtension extends BaseExtension {
    @Override
    public String getName() {
        return "report_catch";
    }
}
