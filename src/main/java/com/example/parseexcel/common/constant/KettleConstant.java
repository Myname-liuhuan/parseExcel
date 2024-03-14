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
    public static final String SUB_PATH = "SubPath";
     


    /**
     * 常见地含有4个转换的作业
     */
    public static final String SCRIPT_NAME_WORK = "ScriptName.kjb";
    public static final String SCRIPT_NAME_WORK_HISTORY = "ScriptName1.kjb";

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
    public static final String SCRIPT_NAME_TRANS05 = "ScriptName05.ktr";

    /**
     * 验证脚本模板文件名称
     */
    public static final String SCRIPT_CHECK_PATH = TEMPLATE_PATH_NO_SPRING + "CheckScript.ktr";


    /**
     * 输出目录
     */
    public static final String WINDOW_OUTPUT_PATH = "D:/Kettle_file/";

    /**
     * 输出次级目录
     */
    public static final String WINDOW_OUTPUT_PATH_SUB = "售后/售后保修/";

    /**
     * 验证脚本文件输出目录
     */
    public static final String WINDOW_OUTPUT_TEST_PATH = WINDOW_OUTPUT_PATH  + "售后/测试脚本/售后保修/";

}
