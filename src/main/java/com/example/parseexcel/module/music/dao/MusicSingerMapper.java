package com.example.parseexcel.module.music.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.parseexcel.module.music.dao.model.MusicSinger;
import com.example.parseexcel.module.music.dao.vo.MusicSingerVO;

@Mapper
public interface MusicSingerMapper extends BaseMapper<MusicSinger> {

    List<MusicSingerVO> getList(MusicSinger musicSinger);
    
}
