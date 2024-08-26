package com.example.parseexcel.module.music.dao.vo;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * 歌手表VO
 */
@Data
public class MusicSingerVO {

    @TableId(type = IdType.ASSIGN_ID) 
    private Long id;

    //歌手名称
    private String name;

    //性别 1男 0女
    private Integer sex;

    //出生日期
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
