package com.example.parseexcel.module.music.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.parseexcel.common.result.CommonResult;
import com.example.parseexcel.module.music.dao.MusicSingerMapper;
import com.example.parseexcel.module.music.dao.model.MusicSinger;
import com.example.parseexcel.module.music.dao.vo.MusicSingerVO;
import com.example.parseexcel.module.music.service.MusicSingerService;

@Service
public class MusicSingerServiceImpl implements MusicSingerService {

    @Autowired
    MusicSingerMapper musicSingerMapper;

    @Override
    public CommonResult<List<MusicSingerVO>> getList(MusicSinger musicSinger) {
        return CommonResult.success(musicSingerMapper.getList(musicSinger));
    }
    
}
