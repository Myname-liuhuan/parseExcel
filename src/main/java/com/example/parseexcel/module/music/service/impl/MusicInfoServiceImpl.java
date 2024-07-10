package com.example.parseexcel.module.music.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.parseexcel.common.result.CommonResult;
import com.example.parseexcel.module.music.dao.MusicInfoMapper;
import com.example.parseexcel.module.music.dao.model.MusicInfo;
import com.example.parseexcel.module.music.dao.vo.MusicInfoVO;
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

    /**
     * 分页查询音乐信息
     */
    @Override
    public CommonResult<Page<MusicInfoVO>> pageList(Integer pageNum, Integer pageSize) {
        Page<MusicInfo> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<MusicInfo> queryWrapper = new LambdaQueryWrapper<>();
        //如果有条件可以在这里queryWrapper中方法添加
        page = musicInfoMapper.selectPage(page, queryWrapper);

        //封装为VO
         List<MusicInfoVO> voList = page.getRecords().stream().map(record -> {
            MusicInfoVO vo = new MusicInfoVO();
            BeanUtils.copyProperties(record, vo);
            return vo;
        }).collect(Collectors.toList());

        Page<MusicInfoVO> voPage = new Page<>();
        BeanUtils.copyProperties(page, voPage);
        voPage.setRecords(voList);
        return CommonResult.success(voPage);
    }
    
}
