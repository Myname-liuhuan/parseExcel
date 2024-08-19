package com.example.parseexcel.module.music.dao.vo;

import org.apache.poi.hpsf.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class MusicInfoVO{

    // id主键
    private Long id;

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

    public String createUser;

    public String updateUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date updateTime;

    public Integer delFlag;


}
