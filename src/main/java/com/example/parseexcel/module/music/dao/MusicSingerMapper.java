package com.example.parseexcel.module.music.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.parseexcel.module.music.dao.model.MusicSinger;
import com.example.parseexcel.module.music.dao.vo.MusicSingerVO;

@Mapper
public interface MusicSingerMapper extends BaseMapper<MusicSinger> {

    List<MusicSingerVO> getList(MusicSinger musicSinger);

    List<MusicSingerVO> pageList(MusicSinger musicSinger, Integer offset, Integer pageSize);

    @Select("SELECT FOUND_ROWS()")
    Integer getTotal();
    
}
