package com.example.parseexcel.module.music.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    @Override
    public CommonResult<Integer> saveMusicSinger(MusicSinger musicSinger) {
        if (musicSinger.getId() == null) {
            return CommonResult.success(musicSingerMapper.insert(musicSinger));
        }
        return CommonResult.success(musicSingerMapper.updateById(musicSinger));
    }

    /**
     * 分页查询
     */
    @Override
    public CommonResult<Page<MusicSingerVO>> pageList(MusicSinger musicSinger, Integer pageNum, Integer pageSize) {
        List<MusicSingerVO> records =  musicSingerMapper.pageList(musicSinger, (pageNum - 1) * pageSize, pageSize);
        int total = musicSingerMapper.getTotal();

        Page<MusicSingerVO> voPage = new Page<>();
        voPage.setRecords(records);
        voPage.setTotal(total);
        voPage.setCurrent(pageNum);
        voPage.setSize(pageSize);

        return CommonResult.success(voPage);
    }
    
}
