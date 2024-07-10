package com.example.parseexcel.module.music.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.parseexcel.module.music.dao.MusicInfoMapper;
import com.example.parseexcel.module.music.dao.model.MusicInfo;
import com.example.parseexcel.module.music.service.MusicInfoService;

@Service
public class MusicInfoServiceImpl implements MusicInfoService {

    @Autowired
    MusicInfoMapper musicInfoMapper;

    /**
     * 保存音乐信息
     */
    @Override
    public int saveMusicInfo(MusicInfo musicInfo) {
       return musicInfoMapper.insert(musicInfo);
    }
    
}
