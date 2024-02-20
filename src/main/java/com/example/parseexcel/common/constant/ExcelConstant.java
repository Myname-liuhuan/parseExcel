package com.example.parseexcel.common.constant;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;

public class ExcelConstant {
    //表头开的行
    public final static int ROWSTARTINDEX = 6;

    //存储表头信息的行
    public final static int HEAD_INFO_ROW_INDEX = 5;

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

    public static final Set<String> whiteSet = Sets.newHashSet("UPDATE_COUNT", "CREATE_TIME", "CREATE_BY"
            , "CREATE_BY_NAME", "UPDATE_TIME", "UPDATE_BY", "UPDATE_BY_NAME", "REMARK");

    public static final String YES = "是";
    public static final String NO = "否";

    public static final String OK = "OK";
    public static final String NG = "NG";
    public static final String NA = "N/A";

    public static final String CHECK_NUMBER = "数量校验";
    public static final String CHECK_VALUE_LIST = "值列表校验";
    public static final String CHECK_SUM_NUMBER = "数字类型总计校验";
    public static final String CHECK_OTHER = "其他校验";



}
