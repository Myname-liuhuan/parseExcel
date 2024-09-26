package com.example.parseexcel.module.music.dao.model;


import java.util.Date;

import org.apache.ibatis.type.JdbcType;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.parseexcel.common.handler.EncryptTypeHandler;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 实体类的变量类型不要使用基本数据类型，而是使用封装类。
 * mybatis进行update的时候会给基本数据类型默认值，但其实这个字段并不需要更新,导致更新结果与预期不一致
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("d_music")
public class MusicInfo {

    // id主键
    @TableId(type = IdType.ASSIGN_ID) //插入时雪花算法生成id
    private Long id;

    // 歌手id
    @TableField(updateStrategy = FieldStrategy.ALWAYS)
    private Long singerId;

    /**
     * 歌手名称
     */
    private String singerName;

    // 音乐文件url地址
    private String musicUrl;

    //音乐图片
    private String imageUrl;

    // 缩略图  //加密
    @TableField(jdbcType = JdbcType.VARCHAR, typeHandler = EncryptTypeHandler.class)
    private String miniImageUrl;

    // 音乐名
    private String musicName;

    // 音乐时长 单位s
    private Integer musicTimeLength;

    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    @TableField(fill = FieldFill.UPDATE)
    private Long updateUser;

    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @TableField(fill = FieldFill.UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @TableField(fill = FieldFill.INSERT)
    private Integer delFlag;
}
