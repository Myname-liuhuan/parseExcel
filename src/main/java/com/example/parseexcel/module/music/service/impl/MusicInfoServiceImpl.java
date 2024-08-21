package com.example.parseexcel.module.music.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.parseexcel.common.constant.SystemConstant;
import com.example.parseexcel.common.result.CommonResult;
import com.example.parseexcel.module.music.dao.MusicInfoMapper;
import com.example.parseexcel.module.music.dao.model.MusicInfo;
import com.example.parseexcel.module.music.dao.vo.MusicInfoVO;
import com.example.parseexcel.module.music.dao.vo.MusicInfoVO2;
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
        if (musicInfo.getId() == null) {
            return musicInfoMapper.insert(musicInfo);
        }else {
            return musicInfoMapper.updateById(musicInfo);
        }
    }

    /**
     * 分页查询音乐信息
     */
    @Override
    public CommonResult<Page<MusicInfoVO>> pageList(MusicInfo musicInfo, Integer pageNum, Integer pageSize) {
        Page<MusicInfo> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<MusicInfo> queryWrapper = new LambdaQueryWrapper<>();
        //如果有条件可以在这里queryWrapper中方法添加
        queryWrapper.eq(MusicInfo::getDelFlag, SystemConstant.DEL_FLAG_NO);
                    
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

    /**
     * 分页查询音乐信息,且关联歌手表获取歌手姓名
     */
    @Transactional
    @Override   
    public CommonResult<Page<MusicInfoVO2>> pageListJoinSong(MusicInfo musicInfo,Integer pageNum, Integer pageSize) {
        List<MusicInfoVO2> records =  musicInfoMapper.pageListJoinSong(musicInfo, (pageNum -1) * pageSize, pageSize);
        int total = musicInfoMapper.getTotal();

        //封装page
        Page<MusicInfoVO2> voPage = new Page<>();
        voPage.setRecords(records);
        voPage.setTotal(total);
        voPage.setCurrent(pageNum);
        voPage.setSize(pageSize);
        //page类里面会自动运算不需要再赋值
        // voPage.setPages(total % pageSize == 0 ? total / pageSize : total / pageSize + 1);
        
        return CommonResult.success(voPage);
    }
    
    
}
