package com.example.parseexcel.module.music.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.parseexcel.common.result.CommonResult;
import com.example.parseexcel.module.music.dao.model.MusicInfo;
import com.example.parseexcel.module.music.dao.vo.MusicInfoVO;
import com.example.parseexcel.module.music.dao.vo.MusicInfoVO2;
import com.example.parseexcel.module.music.service.MusicInfoService;

@RestController
@RequestMapping("/media/music")
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
        return musicInfoService.pageList(pageNum == null? 1 :pageNum, pageSize == null? 10 : pageSize);
    }
    
    /**
     * 分页查询且关联歌手表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/pageListJoinSong")
    public CommonResult<Page<MusicInfoVO2>> pageListJoinSong(Integer pageNum, Integer pageSize){
        return musicInfoService.pageListJoinSong(pageNum == null || pageNum <= 0? 1 :pageNum, pageSize == null? 10 : pageSize);
    }
}
