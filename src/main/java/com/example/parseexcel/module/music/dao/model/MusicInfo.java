package com.example.parseexcel.module.music.dao.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("d_music")
public class MusicInfo {

    // id主键
    @TableId(type = IdType.NONE) //插入时雪花算法生成id
    private int id;

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

    // 无参构造方法
    public MusicInfo() {}

    // 全参构造方法
    public MusicInfo(int id, String musicUrl, String imageUrl, String miniImageUrl, String musicName, int musicTimeLength) {
        this.id = id;
        this.musicUrl = musicUrl;
        this.imageUrl = imageUrl;
        this.miniImageUrl = miniImageUrl;
        this.musicName = musicName;
        this.musicTimeLength = musicTimeLength;
    }
}
