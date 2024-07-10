package com.example.parseexcel.module.music.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.parseexcel.common.result.CommonResult;
import com.example.parseexcel.module.music.dao.model.MusicInfo;
import com.example.parseexcel.module.music.dao.vo.MusicInfoVO;
import com.example.parseexcel.module.music.service.MusicInfoService;

@RestController
@RequestMapping("/music")
public class MusicInfoController {
    @Autowired
    MusicInfoService musicInfoService;

    @GetMapping("/saveMusicInfo")
    public int saveMusicInfo(MusicInfo musicInfo){
        return musicInfoService.saveMusicInfo(musicInfo);
    }

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/pageList")
    public CommonResult<Page<MusicInfoVO>> pageList(Integer pageNum, Integer pageSize){
        return musicInfoService.pageList(pageNum, pageSize);
    }
    
}
