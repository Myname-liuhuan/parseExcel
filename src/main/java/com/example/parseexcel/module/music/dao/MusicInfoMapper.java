package com.example.parseexcel.module.music.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.parseexcel.module.music.dao.model.MusicInfo;
import com.example.parseexcel.module.music.dao.vo.MusicInfoVO2;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface MusicInfoMapper extends BaseMapper<MusicInfo> {
    List<MusicInfoVO2> pageListJoinSong(Integer offset, Integer pageSize);

    @Select("SELECT count(*) AS total FROM d_music")
    Integer getTotal();
    
}