package com.example.parseexcel.module.music.dao.model;


import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
    private Long singerId;

    // 音乐文件url地址
    private String musicUrl;

    //音乐图片
    private String imageUrl;

    // 缩略图
    private String miniImageUrl;

    // 音乐名
    private String musicName;

    // 音乐时长 单位s
    private Integer musicTimeLength;

    private Long createUser;

    private Long updateUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private Integer delFlag;
}
