package com.example.parseexcel.module.music.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.parseexcel.common.result.CommonResult;
import com.example.parseexcel.module.music.dao.model.MusicInfo;
import com.example.parseexcel.module.music.dao.vo.MusicInfoVO;


public interface MusicInfoService {
    int saveMusicInfo(MusicInfo musicInfo);

    CommonResult<Page<MusicInfoVO>> pageList(Integer pageNum, Integer pageSize);
}
