package com.example.parseexcel.module.music.dao.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * 包含联查后得到歌手名称的返回结果
 */
@Data
public class MusicInfoVO2 {

    // id主键
    private String id;

       // 歌手id
    private String singerId;

    // 音乐文件url地址
    private String musicUrl;

    //音乐图片
    private String imageUrl;

    // 缩略图
    private String miniImageUrl;

    // 音乐名
    private String musicName;

    // 音乐时长 单位s
    private int musicTimeLength;

    // 演唱者
    private String singerName;

    private Long createUser;

    private Long updateUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private Integer delFlag;



}
