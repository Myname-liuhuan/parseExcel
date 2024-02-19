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
            .put(7, "colNo")
            .put(8, "colName")
            .put(9, "colId")
            .put(10, "notNull")
            .put(11, "colVar")
            .put(12, "colByte")
            .put(13, "colPK")
            .put(14, "colFK")
            .put(15, "colRule")
            .put(16, "distinguish")
            .put(17, "handle")
            .put(18, "sourceSystem")
            .put(19, "sourceTableName")
            .put(20, "sourceColName")
            .put(21, "sourceColVar")
            .put(22, "checkFlag")
            .build();

}
