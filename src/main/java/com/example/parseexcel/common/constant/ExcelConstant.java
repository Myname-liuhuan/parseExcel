package com.example.parseexcel.common.constant;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

public class ExcelConstant {
    //表头开的行
    public final static int ROWSTARTINDEX = 6;

    //要读取的sheet页
    public final static int SHEETINDEX = 2;

    //开始有数据的行数
    public final static int COLSTARTINDEX = 7;

    /**
     * 列在excel中的索引及其对应的实体类参数
     */
    public static final Map<Integer, String> COLMAP = ImmutableMap.<Integer, String>builder()
            .put(1, "北京")
            .put(2, "上海")
            .build();

}
