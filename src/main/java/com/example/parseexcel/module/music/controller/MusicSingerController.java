package com.example.parseexcel.module.music.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.parseexcel.common.result.CommonResult;
import com.example.parseexcel.module.music.dao.model.MusicSinger;
import com.example.parseexcel.module.music.dao.vo.MusicSingerVO;
import com.example.parseexcel.module.music.service.MusicSingerService;

@RestController
@RequestMapping("/media/singer")
public class MusicSingerController {
    
    @Autowired
    MusicSingerService musicSingerService;
   
    /**
     * 获取所有歌手信息
     * @return
     */
    @GetMapping("/getList")
    public CommonResult<List<MusicSingerVO>> getList(MusicSinger musicSinger) {
        return musicSingerService.getList(musicSinger);
    }

    @PostMapping("/saveMusicSinger")
    public CommonResult<Integer> saveMusicSinger(@RequestBody MusicSinger musicSinger){
        return musicSingerService.saveMusicSinger(musicSinger);
    }
    
}
