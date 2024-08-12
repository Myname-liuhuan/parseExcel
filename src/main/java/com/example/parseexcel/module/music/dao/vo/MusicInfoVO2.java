package com.example.parseexcel.module.music.dao.vo;

import lombok.Data;

/**
 * 包含联查后得到歌手名称的返回结果
 */
@Data
public class MusicInfoVO2 {

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

    // 演唱者
    private String singerName;


}
