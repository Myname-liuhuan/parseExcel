package com.example.parseexcel.dao.model;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 迁移数据映射关系表
 * @author liuhuan
 * @date 2024/4/5
 */
@Data
@TableName("transfer_db_mapping")
public class TransferDbMappingTable {

    /**
     * 中间表名称
     */
    String middleTableName;

    /**
     * 要件名称
     */
    String yjName;

    /**
     * oracle中间表名称
     */
    String oracleMiddleTableName;

    /**
     * 源表名称
     */
    String sourceTableName;

    /**
     * 目标表名称
     */
    String targetTableName;

    /**
     * 目标表数据库
     */
    String targetDatabaseName;



}
