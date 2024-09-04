package com.example.parseexcel.module.music.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.parseexcel.common.result.CommonResult;
import com.example.parseexcel.module.music.dao.model.MusicSinger;
import com.example.parseexcel.module.music.dao.vo.MusicSingerVO;

public interface MusicSingerService {
    
    CommonResult<List<MusicSingerVO>> getList(MusicSinger musicSinger);

    CommonResult<Integer> saveMusicSinger(MusicSinger musicSinger);

    CommonResult<Page<MusicSingerVO>> pageList(MusicSinger musicSinger, Integer pageNum, Integer pageSize);

}
