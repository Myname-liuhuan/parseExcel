package com.example.parseexcel.module.image.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.parseexcel.module.image.dao.model.LocalImageInfo;

@Mapper
public interface LocalImageInfoMapper extends BaseMapper<LocalImageInfo> {


    @Delete("truncate table t_local_image_info")
    Integer deleteAll();
    
}
