package com.example.parseexcel.common.constant;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * kettle脚本相关常量
 * @author liuhuan
 * @date 2023/12/17
 */
public class KettleConstant {


    /**
     * kettle脚本模板在resource目录下的路径
     */
    private static final String TEMPLATE_PATH = "classpath:static/template/kettle/";

    /**
     * 在原生Java方式导出的时候用到的模板路径
     */
    public static final String TEMPLATE_PATH_NO_SPRING = "static/template/kettle/";

    /**
     * 现存于模板文件中的占位符(属于必须要传的参数)
     */
    public static final String SCRIPT_NAME= "ScriptName";
    public static final String TARGET_TABLE_NAME = "TargetTableName";
    public static final String MIDDLE_TABLE_NAME = "MiddleTableName";


    /**
     * 常见地含有4个转换的作业
     */
    public static final String SCRIPT_NAME_WORK = "ScriptName.kjb";

    /**
     * 源库到中间库
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

    /**
     * 输出目录
     */
    public static final String WINDOW_OUTPUT_PATH = "D:/Kettle_file/";

    /**
     * 输出次级目录
     */
    public static final String WINDOW_OUTPUT_PATH_SUB = "售后/售后保修/";


}
