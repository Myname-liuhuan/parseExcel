package com.example.parseexcel.entity;

import lombok.Data;

/**
 * 每行数据
 */
@Data
public class CSYLColDataEntity {
    
    String colNo;

    String colName;

    String colId;

    String notNull;

    String colVar;

    String colByte;

    String colPK;

    String colFK;

    String colRule;

    String distinguish;

    String handle;

    String sourceSystem;

    String sourceTableName;

    String sourceColName;

    String sourceColVar;

    String checkFlag;

    String checkType;

    String checkText;

    String testDone;

    String colRemark;
}
