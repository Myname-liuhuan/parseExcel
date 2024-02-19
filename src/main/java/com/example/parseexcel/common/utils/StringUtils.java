package com.example.parseexcel.common.utils;

public class StringUtils {

    /**
     * 
     * 裁剪传入的String
     * 规则如下
     * 业务数据迁移要件_零件保修_CHARGE_BACK追算.xlsx
     * 裁剪为： 
     * 零件保修_CHARGE_BACK追算
     * @param sourceStr
     * @return
     */
    public static String cutString(String sourceStr){
        return sourceStr.substring(sourceStr.indexOf("_"), sourceStr.lastIndexOf("."));
    }

    public static String cutLastString(String sourceStr){
        return sourceStr.substring(sourceStr.lastIndexOf("_"), sourceStr.lastIndexOf("."));
    }

    
}
