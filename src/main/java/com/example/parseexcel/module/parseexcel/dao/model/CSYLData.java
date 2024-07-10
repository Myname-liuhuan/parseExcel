package com.example.parseexcel.module.parseexcel.dao.model;

import lombok.Data;

/**
 * 要件数据
 */
@Data
public class CSYLData implements Cloneable{

    /**
     * sheet(1) 下面的源文件名称
     */
    String sourceFileName;

    /**
     * sheet(0) 即sheet()
     * 示例： ST-05_业务数据测试用例
     */
    String shortProjectName;

    /**
     * sheet(0)
     * 示例： ST-06_业务数据测试用例_售后保修_供应商
     *        ST-05_基础数据测试用例_售后保修_供应商
     */
    String projectName;

    /**
     * sheet(0)
     * 格式 yyyy/mm/dd
     */
    String createDate;

    /**
     * sheet(0)
     * 作者
     */
    String writerName;

    /**
     * sheet(2)
     * 表名(中文)
     */
    String tableNameZh;

     /**
     * sheet(2)
     * 表名(英文)
     */
    String tableNameEn;

     /**
     * sheet(2)
     * 源表名总和
     */
    String sourceTableNameAll;

    @Override
    public Object clone(){
        CSYLData csylData = null;
        try {
            csylData = (CSYLData) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return csylData;
    }
}
