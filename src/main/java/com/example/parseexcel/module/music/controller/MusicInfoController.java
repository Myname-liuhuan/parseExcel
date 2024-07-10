package com.example.parseexcel.module.music.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.parseexcel.module.music.dao.model.MusicInfo;
import com.example.parseexcel.module.music.service.MusicInfoService;

@RestController
@RequestMapping("/music")
public class MusicInfoController {
    @Autowired
    MusicInfoService musicInfoService;

    @GetMapping("/saveMusicInfo")
    int saveMusicInfo(MusicInfo musicInfo){
        return musicInfoService.saveMusicInfo(musicInfo);
    }
    
}
