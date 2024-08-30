package com.example.parseexcel.module.music.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.parseexcel.common.result.CommonResult;
import com.example.parseexcel.module.music.dao.model.MusicInfo;
import com.example.parseexcel.module.music.dao.vo.MusicInfoVO;
import com.example.parseexcel.module.music.dao.vo.MusicInfoVO2;


public interface MusicInfoService {
    CommonResult<Integer> saveMusicInfo(MusicInfo musicInfo);

    CommonResult<Page<MusicInfoVO>> pageList(MusicInfo musicInfo, Integer pageNum, Integer pageSize);

    CommonResult<Page<MusicInfoVO2>> pageListJoinSong(MusicInfo musicInfo, Integer pageNum, Integer pageSize);

    CommonResult<Integer> deleteById(Long id);
    CommonResult<Integer> logicalDeleteById(Long id);

    CommonResult<Integer> logicalBatchDeleteByIds(List<MusicInfo> list);




}
