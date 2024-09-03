package com.example.parseexcel.module.music.dao.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * 歌手表VO
 */
@Data
public class MusicSingerVO {

    private String id;

    //歌手名称
    private String name;

    //出生日期
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;

/*** 以下为公共字段 ***/
    private Long createUser;

    private Long updateUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private Integer delFlag;
}
