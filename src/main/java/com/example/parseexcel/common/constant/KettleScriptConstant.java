package com.example.parseexcel.common.constant;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * kettle脚本相关常量
 * @author liuhuan
 * @date 2023/12/17
 */
public class KettleScriptConstant {

    /**
     * 需要替换的参数集合
     */
    public static final Map<String, String> REPLACE_MAP = ImmutableMap.<String, String>builder()
            //脚本名称
            .put("ScriptName", "ScriptName")
            //目标库名称
            .put("TargetDatabaseName", "TargetDatabaseName")
            //中间表名称
            .put("MiddleDatabaseName", "MiddleDatabaseName")
            .build();

    /**
     * kettle脚本模板在resource目录下的路径
     */
    private static final String TEMPLATE_PATH = "classpath:static/template/kettle/";

    /**
     * 常见地含有4个转换的作业
     */
    public static final String SCRIPT_NAME_WORK = TEMPLATE_PATH + "ScriptName.kjb";

    /**
     * 转移1：源库到中间库
     */
    public static final String SCRIPT_NAME_TRANS01 = "ScriptName01.ktr";

    /**
     * 中间库到目标库
     */
    public static final String SCRIPT_NAME_TRANS02 = "ScriptName02.ktr";

    /**
     * 脚本迁移成功
     */
    public static final String SCRIPT_NAME_TRANS03 = "ScriptName03.ktr";

    /**
     * 脚本迁移失败
     */
    public static final String SCRIPT_NAME_TRANS04 = "ScriptName04.ktr";
}
