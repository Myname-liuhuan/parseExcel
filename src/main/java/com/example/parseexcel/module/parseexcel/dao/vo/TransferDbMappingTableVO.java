package com.example.parseexcel.module.parseexcel.dao.vo;

import lombok.Data;

/**
 * 迁移数据映射关系表 VO
 * @author liuhuan
 * @date 2024/4/5
 */
@Data
public class TransferDbMappingTableVO {

    /**
     * 中间表名称
     */
    String middleTableName;

    /**
     * 要件名称
     */
    String yjName;


    /**
     * 目标表名称
     */
    String targetTableName;



}
